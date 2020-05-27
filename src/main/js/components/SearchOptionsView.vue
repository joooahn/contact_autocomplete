<template>
    <div class="options">
        <label
                class="options__checkbox"
                v-for="option in options"
                :key="option"
        >
            <input
                    type="checkbox"
                    :id="'$_checkbox_' + option"
                    :checked="isActive(option)"
                    :disabled="isActive(option)"
                    @click="$emit('input', option)"
            />
            <!-- checkbox css 적용을 위한 label -->
            <label :for="'$_checkbox_' + option"></label>
            {{option}}
        </label>
    </div>
</template>

<script>
	export default {
		props : {
			options : {
				type : Array,
				required : true,
				description : "선택할 수 있는 옵션 목록"
			},
			value : {
				type : String,
				description : "v-model 용도"
			}
		},
		methods : {
			isActive(label) {
				return this.value === label;
			}
		}
	}
</script>

<style scoped>
    input {
        display: none;
    }

    input + label {
        background-color: #fafafa;
        border: 1px solid #cacece;
        padding: 9px;
        top: 4px;
        border-radius: 3px;
        display: inline-block;
        position: relative;
    }

    input + label:active, input:checked + label:active {
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px rgba(0, 0, 0, 0.1);
    }

    input:checked + label {
        background-color: #e9ecee;
        border: 1px solid #adb8c0;
        color: #99a1a7;
    }

    input:checked + label:after {
        content: '\2714';
        font-size: 14px;
        position: absolute;
        top: 0;
        left: 3px;
        color: black;
    }

    .options {
        display: flex;
    }

    .options__checkbox {
        background-color: aliceblue;

        text-align: center;

        flex: 1;

        padding: 0.4rem;
        margin-right: 1rem;

        border: black solid 0.1rem;
        border-radius: 0.2rem;
    }

    .options__checkbox:last-child {
        margin-right: 0;
    }
</style>
