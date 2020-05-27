<template>
	<div>
		<div>
			<button type="button" @click="menu='검색'">검색</button>
			<button type="button" @click="menu='cowork';loadDepartments();">cowork</button>
			<button type="button" @click="menu='직책';loadPositions()">직책</button>
			<br><br>
		</div>
		<div>
			아이디: <input type="text" v-model="userId" @blur="getMy">&nbsp;
			<span>{{userEmail}}</span>&nbsp;
			<span v-if="myDepartment">소속 : {{myDepartment.name}}</span>&nbsp;
			<span v-if="myPosition">직책 : {{myPosition.name}}</span>&nbsp;
		</div>
		<div v-show="menu=='검색'">
			<div>
				<input placeholder="검색어를 입력해주세요."
					   style="border:2px solid black;width:50%;padding:5px;margin:5px 0px;"
					   @input="search($event.target.value)"
				/>
			</div>
			<div style="vertical-align: top">
				<div class="search" style="float:left;width:70%;margin-right:5px;">
					<text-view
							:userId="userId"
							:userEmail="userEmail"
							:contacts="contacts"
							:keyword="keyword"
							@refresh="search(keyword)"
					/>
				</div>
				<div class="time" style="float:left;width:25%">
					평균 시간: {{average || 0}} ms
					<div v-for="(time, index) in times">
						<span :class="{first_row:index==0}">{{time}} ms</span>
					</div>
				</div>
			</div>
		</div>
		<div v-show="menu=='cowork'">
			<table>
				<tr>
					<td>id</td>
					<td>name</td>
				</tr>
				<tr v-for="(department, index) in departments">
					<td>{{department.id}}</td>
					<td>{{department.name}}</td>
					<td>
						<button v-if="!department.cowork" type="button" @click="toggleCowork(department.id, true)">cowork 등록</button>
						<button v-if="department.cowork" type="button" @click="toggleCowork(department.id, false)" style="color:red;">cowork 해제</button>
						<button v-if="myDepartment==null || myDepartment.id != department.id" type="button" @click="insertDepartment(department.id)">내부서 등록</button>
						<span v-else>내 부서</span>
					</td>
				</tr>
			</table>
		</div>
		<div v-show="menu=='직책'">
			<table>
				<tr>
					<td>id</td>
					<td>name</td>
					<td>order</td>
				</tr>
				<tr>
					<td>신규</td>
					<td><input type="text" v-model="newPositionName"></td>
					<td><input type="text" v-model="newPositionPriority" size="3"></td>
					<td>
						<button type="button" @click="insertPosition">신규 등록</button>
					</td>
				</tr>
				<tr v-for="(position, index) in positions">
					<td>{{position.id}}</td>
					<td>{{position.name}}</td>
					<td>{{position.priority}}</td>
					<td>
						<span v-if="myPosition && myPosition.id==position.id">내 직책</span>
						<button v-else type="button" @click="mappingPosition(position.id)">직책 부여</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	import SearchOptionsView from "@/components/SearchOptionsView";
	import TextView from "@/components/TextView";

	import * as _ from "lodash";

	const options = ["STARTS_WITH", "CONTAINS", "ENDS_WITH"];

	export default {
		components : {
			TextView,
			SearchOptionsView
		},
		data() {
			return {
				userEmail : "",
				menu : "검색",
				contacts : [], // 검색 결과
				keyword : "", // 검색어
				times : [], // 서버 응답 시간들을 저장하고 있는 배열
				userId : "",
				runningKeyword : "",
				emailTo : "",
				emailFrom : "",
				departments:[],
				positions: [],
				myDepartment: null,
				newPositionName : "",
				newPositionPriority: "",
				myPosition: null,
			}
		},
		methods : {
			/**
			 * 서버에서 자동 완성 요청을 보내는 메소드
			 */
			search : _.debounce(function(keyword) {
				if (keyword) {
					let startTime = new Date().getTime();
                    this.runningKeyword = keyword;
                    this.$http.get(`/contacts/search?searcherId=${this.userId}&searchText=${keyword}`).then(result => {
                        if(keyword != this.runningKeyword) {
                            return;
                        }
                        this.contacts = result.data;
                        this.keyword = keyword;
                        this.times.splice(0, 0, new Date().getTime() - startTime);
                    })
				} else {
					this.contacts = [];
				}
			}, 100),
			getMy() {
				this.$http.get(`/contacts/${this.userId}/email`)
					.then(result => {
						if (result.data) {
							this.userEmail = result.data;
						} else {
							this.userEmail = "존재하지 않는 유저";
						}
					}).catch(e=>{
					this.userEmail = "존재하지 않는 유저";
				});
				this.$http.get(`/contacts/${this.userId}/departments`)
					.then(result => {
						this.myDepartment = result.data;
					});
				this.$http.get(`/contacts/${this.userId}/position`)
					.then(result => {
						this.myPosition = result.data;
					});
			},
			loadDepartments() {
				this.$http.get(`/contacts/${this.userId}/departmentsWithCowork`)
					.then(rep=>{
						this.departments = rep.data;
					});
			},
			toggleCowork(departmentId, register) {
				this.$http({
					url : `/contacts/${this.userId}/coworkDepartments/${departmentId}`,
					method : register ? "post" : "delete",
				}).then(rep=>{
					this.loadDepartments();
				});
			},
			insertDepartment(departmentId) {
				this.$http.put(`/contacts/${this.userId}/departments/${departmentId}`)
					.then(()=>{
						this.getMy();
					});
			},
			loadPositions() {
				this.$http.get("/retrievePosition")
					.then(rep=>{
						this.positions = rep.data;
					});
			},
			insertPosition() {
				this.$http.post("/positions", {
					name: this.newPositionName,
					priority: this.newPositionPriority
				}).then(()=> {
					this.loadPositions();
				}).catch(ex => {
					alert(ex.response.data.message);
				})
			},
			mappingPosition(positionId) {
				this.$http.put(`/contacts/${this.userId}/positionId/${positionId}`)
					.then(()=>{
						this.getMy();
					})
					.catch(e=>{
						alert(e.response.data.message);
					});
			}
		},
		computed : {
			average() {
				let times = [...this.times];
				let average = times.reduce((sum, cur) => sum + cur, 0) / times.length;

				return Math.round(average);
			}
		},
	}
</script>

<style>
	html {
		display: flex;
		flex-direction: column;
		height: 100vh;
	}

	body {
		flex: 1;
		display: flex;
		flex-direction: column;
	}

	#app {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

	* {
		font-size: 17px;
	}

	button {
		font-size: 12px;
	}

	.search {
		display: flex;
		flex-direction: column;
	}

	.search > * {
		border: black solid 0.1rem;
		padding: 0.2rem;
	}

	.time {
		border: black solid 0.1rem;
		padding: 0.2rem;

		flex: 1;
		overflow: auto;
	}
	.first_row {
		color:blue; font-weight:bold;
	}
</style>
