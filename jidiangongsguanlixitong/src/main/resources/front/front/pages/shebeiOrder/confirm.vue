<template>
	<view class="content">
		<form>

			<view class="cu-form-group">
				<view class="title">购买清单</view>
			</view>
			<view class="cu-form-group select">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
					class="title">客户</view>
				<picker @change="kehuTypesChange" :value="kehuTypesIndex" :range="kehuTypesOptions"
					range-key="kehuName">
					<view
						:style='{"padding":"0 24rpx","boxShadow":"0px 0px 0px rgba(0, 0, 0, 0.16)","margin":"0","borderColor":"var(--publicMainColor)","backgroundColor":"#fff","color":"#333","textAlign":"left","borderRadius":"0 180rpx 180rpx 0","borderWidth":"4rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}'
						class="uni-input picker-select-input">{{kehuValue?kehuValue:"请选择客户"}}</view>
				</picker>
			</view>
			<view v-for="(item,index) in orderGoods " v-bind:key="index" class="cu-form-group">
				<image class="avator" :src="baseUrl+item.shebeiPhoto" mode=""></image>
				<view class="title" style="width: 75%;">
					<view style="margin-top: -50rpx;">{{item.shebeiName}}</view>
					<view>
						x{{item.buyNumber}}
						<text style="margin-left: 30upx;color: red;">￥{{item.shebeiNewMoney}}</text>
					</view>
				</view>
			</view>
			<view @tap="onAddressTap" class="cu-form-group">
				<view class="title">总价</view>
				<view>
					<text v-if="shebeiOrderPaymentTypes == 1">原价：￥{{(maxNewMouey).toFixed(2)}}</text>
					<view v-if="shebeiOrderPaymentTypes == 1"></view>
					<text v-if="shebeiOrderPaymentTypes == 1">折扣价：￥{{(maxNewMouey * zhekou).toFixed(2)}}</text>
					<text v-if="shebeiOrderPaymentTypes == 2">{{(maxNewMouey).toFixed(2)}}积分</text>
				</view>
			</view>
		</form>
		<view class="padding" style="text-align: center;">
			<button @tap="onSubmitTap()" class="bg-red lg">确认支付</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {}, //登录用户
				orderGoods: [], //展示数据
				maxNewMouey: 0, //总价格
				shebeiOrderPaymentTypes: 1, //是什么支付类型
				zhi: [{
						id: 1,
						val: "余额"
					},
					{
						id: 2,
						val: "积分"
					},
				],
				zhekou: 1, //折扣
				kehuTypesOptions: [],
				kehuTypesIndex: 0,
				kehuValue: '',
				kehuId: '',
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			let kehu_typesParams = {
				page: 1,
				limit: 100,
			}
			let kehu_typesRes = await this.$api.page(`kehu`, kehu_typesParams);
			
			this.kehuTypesOptions = kehu_typesRes.data.list
			
			
			// 获取订单信息，座位信息
			this.orderGoods = uni.getStorageSync('orderGoods');
			if (this.orderGoods.length > 0) {
				for (let i = 0; i < this.orderGoods.length; i++) {
					this.maxNewMouey = this.maxNewMouey + parseFloat(this.orderGoods[i].shebeiNewMoney) * this
						.orderGoods[i].buyNumber
				}
			}
			uni.removeStorageSync("orderGoods")
		},
		async onShow() {
			let _this = this
			let table = uni.getStorageSync("nowTable");
			let userRes = await _this.$api.session(table)
			_this.user = userRes.data

			let huiyuandengjiTypesRes = await _this.$api.page("dictionary", {
				dicCode: "huiyuandengji_types",
				dicName: "会员等级类型",
				codeIndexStart: _this.user.huiyuandengjiTypes,
				codeIndexEnd: _this.user.huiyuandengjiTypes,
			})
			if (huiyuandengjiTypesRes.data.list.length > 0) {
				_this.zhekou = Number(huiyuandengjiTypesRes.data.list[0].beizhu);
			}


		},
		methods: {
			// 下拉变化
			kehuTypesChange(e) {
				this.kehuTypesIndex = e.target.value
				this.kehuValue = this.kehuTypesOptions[this.kehuTypesIndex].kehuName
				this.kehuId = this.kehuTypesOptions[this.kehuTypesIndex].id
			},
			
			async onSubmitTap() {
				let _this = this;
				let table = uni.getStorageSync("nowTable");
				uni.showModal({
					title: '提示',
					content: '是否确认支付',
					success: async function(res) {
						
						if (res.confirm) {
							if(_this.kehuId==''){
								uni.showToast({
									title: '请选择客户',
									duration: 2000
								});
								return
							}
							let data = {
								shebeis: JSON.stringify(_this.orderGoods),
								yonghuId: _this.user.id,
								shebeiOrderPaymentTypes: _this.shebeiOrderPaymentTypes,
								kehuId:_this.kehuId
							}
							await _this.$api.requestConditionDataGet('shebeiOrder', 'order', null, data);
							_this.$utils.jump('/pages/shebeiOrder/list');
						}
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.avator {
		width: 150upx;
		height: 150upx;
		margin: 20upx 0;
	}
</style>