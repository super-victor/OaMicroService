<template>
	<view>
		<no-permission></no-permission>
	</view>
</template>

<script>
import NoPermission from '../../components/common/NoPermission.vue'
	export default {
		components:{
			NoPermission
		},
		data() {
			return {
				title : '消息中心'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style scoped lang='scss'>

</style>
