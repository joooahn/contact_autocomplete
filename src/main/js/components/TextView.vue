<style scoped>
    td {
        padding-right:5px;
    }
    .flag_area {
        color:lightgray;cursor:pointer;
    }
    .vip {
        color:red!important;
    }
    .cowork {
        color:green!important;
    }
</style>

<template>
    <div>
        <table>
        <tr
            v-for="(contact, index) in contacts"
            :key="contact.id">
            <td :class="{vip:contact.vip}" class="flag_area" @click="toggleVip(index)">VIP</td>
            <td :class="{cowork:contact.cowork}" class="flag_area" @click="toggleVip(index)">co-work</td>
            <td>{{contact.id}}</td>
            <td v-html="highlight(contact.name)"/>
            <td v-html="highlight(contact.email)"/>
            <td>{{contact.department}}</td>
            <td>{{contact.position}}</td>
            <td>
                <button type="button" @click="clickEmail(index)">이메일 갱신</button>
            </td>
            <br>
        </tr>
        </table>
    </div>
</template>

<script>
	export default {
		props : {
		    userId : {
		        type: String,
                required: true,
            },
            userEmail : {
		        type: String,
                required: true,
            },
			contacts : {
				type : Array,
				required : true,
				description : "화면에 표시될 단어들"
			},
			keyword : {
				type : String,
				required : true,
				description : "강조 표시할 키워드"
			}
		},
		methods : {
			highlight(text) {
				return (text || "").trim().replace(this.regex,
					"<span style='color: mediumseagreen'>" + this.keyword + "</span>");
			},
            clickEmail(index) {
                this.$http.post("/private/transmission", {
                    to: this.contacts[index].email,
                    from: this.userEmail,
                }).then(() => {
                    this.$emit("refresh")
                })
            },
            toggleVip(index) {
                this.$http.post("/private/vip", {
                    to: this.contacts[index].id,
                    from: this.userId,
                    vip: !this.contacts[index].vip
                }).then(() => {
                    this.$emit("refresh")
                })
            }
		},
		computed : {
			regex() {
				return new RegExp(this.keyword, 'g');
			}
		}
	}
</script>