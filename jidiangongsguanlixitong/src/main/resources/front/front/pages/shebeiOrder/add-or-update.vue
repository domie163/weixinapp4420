<template>
    <view class="content">
        <form class="app-update-pv">
                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">机电设备名称</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.shebeiName" v-model="shebei.shebeiName" placeholder="机电设备名称"></input>
                 </view>
                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">机电设备编号</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.shebeiUuidNumber" v-model="shebei.shebeiUuidNumber" placeholder="机电设备编号"></input>
                 </view>
                <view
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(218, 220, 219, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"108rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">机电设备照片</view>
                    <view class="right-input" style="padding:0;textAlign:left">
                        <image
                                :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
                                class="avator" v-if="shebei.shebeiPhoto" :src="baseUrl+shebei.shebeiPhoto" mode="aspectFill">
                        </image>
                        <image
                                :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
                                class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
                    </view>
                </view>
							                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">机电设备类型</view>
                    <input disabled
                           :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                           :disabled="ro.shebeiValue"
                           v-model="shebei.shebeiValue"
                           placeholder="机电设备类型"></input>
                </view>
                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">机电设备库存</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.shebeiKucunNumber" v-model="shebei.shebeiKucunNumber" placeholder="机电设备库存"></input>
                 </view>
                <!--<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">机电设备</view>
                    <picker @change="shebeiChange" :value="shebeiIndex" :range="shebeiOptions" range-key="shebeiName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 16rpx var(--publicSubColor) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"20rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.shebeiId?ruleForm.shebeiName:"请选择机电设备"}}</view>
                    </picker>
                </view>-->
                <!--<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">客户</view>
                    <picker @change="kehuChange" :value="kehuIndex" :range="kehuOptions" range-key="kehuName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 16rpx var(--publicSubColor) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"20rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.kehuId?ruleForm.kehuName:"请选择客户"}}</view>
                    </picker>
                </view>-->
                <!--<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">用户</view>
                    <picker @change="yonghuChange" :value="yonghuIndex" :range="yonghuOptions" range-key="yonghuName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 16rpx var(--publicSubColor) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"20rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.yonghuId?ruleForm.yonghuName:"请选择用户"}}</view>
                    </picker>
                </view>-->
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">订单编号</view>
                <input   disabled
                         :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                         :disabled="ro.shebeiOrderUuidNumber" type="text" v-model="ruleForm.shebeiOrderUuidNumber" placeholder="订单编号"></input>
            </view>
                <!-- 级联表的表id -->
                <input type="hidden" :value="shebei.id" id="shebeiId" name="shebeiId"/>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">购买数量</view>
                    <input   disabled
                             :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                             :disabled="ro.buyNumber" type="number" v-model="ruleForm.buyNumber" placeholder="购买数量"></input>
                </view>
                <view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">订单类型</view>
                    <picker @change="shebeiOrderTypesChange" :value="shebeiOrderTypesIndex" :range="shebeiOrderTypesOptions" range-key="indexName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"0px","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.shebeiOrderTypes?ruleForm.shebeiOrderValue:"请选择订单类型"}}</view>
                    </picker>
                </view>

            <view class="btn">
                <button
                        :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"var(--publicMainColor)","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
                        @tap="onSubmitTap" class="bg-red">提交</button>
            </view>
        </form>

					<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="insertTimeConfirm"
                              ref="insertTime" themeColor="#333333"></w-picker>
					<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="createTimeConfirm"
                              ref="createTime" themeColor="#333333"></w-picker>


    </view>
</template>

<script>
    import wPicker from "@/components/w-picker/w-picker.vue";

    export default {
        data() {
            return {
                cross: '',
                ro:{
					shebeiOrderUuidNumber: true,
					shebeiId: false,
					kehuId: false,
					yonghuId: false,
					buyNumber: true,
					shebeiOrderTruePrice: false,
					shebeiOrderTypes: false,
					insertTime: false,
					createTime: false,
        },
            ruleForm: {
					shebeiOrderUuidNumber: this.getUUID(),//数字
					shebeiId: '',
					kehuId: '',
					yonghuId: '',
					buyNumber: '',
					shebeiOrderTruePrice: '',
					shebeiOrderTypes: '',//数字
					shebeiOrderValue: '',//数字对应的值
					insertTime: '',
					createTime: '',
            },
            // 登陆用户信息
            user: {},
            shebeiOrderTypesOptions: [],
            shebeiOrderTypesIndex : 0,
            shebeiOptions: [],//机电设备
            shebeiIndex : 0,//机电设备下标
            kehuOptions: [],//客户
            kehuIndex : 0,//客户下标
            yonghuOptions: [],//用户
            yonghuIndex : 0,//用户下标

        }
        },
        components: {
            wPicker
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
        async onLoad(options) {
            let shebeiParams = {
                page: 1,
                limit: 100,
            }
            let shebeiData = await this.$api.page(`shebei`, shebeiParams);
            this.shebeiOptions = shebeiData.data.list;
            let kehuParams = {
                page: 1,
                limit: 100,
            }
            let kehuData = await this.$api.page(`kehu`, kehuParams);
            this.kehuOptions = kehuData.data.list;
            let yonghuParams = {
                page: 1,
                limit: 100,
            }
            let yonghuData = await this.$api.page(`yonghu`, yonghuParams);
            this.yonghuOptions = yonghuData.data.list;
		/*下拉框*/
			let shebeiOrderTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'shebei_order_types',
            }
			let shebeiOrderTypes = await this.$api.page(`dictionary`, shebeiOrderTypesParams);
			this.shebeiOrderTypesOptions = shebeiOrderTypes.data.list


            // 如果是更新操作
            if (options.id) {
                this.ruleForm.id = options.id;
                // 获取信息
                let res = await this.$api.info(`shebeiOrder`, this.ruleForm.id);
                this.ruleForm = res.data;
            }
            if(options.shebeiOrderId){
                this.ruleForm.shebeiOrderId = options.shebeiOrderId;
            }
            // 跨表
            // this.styleChange()
        },
        methods: {
            shebeiChange(e) {
                this.shebeiIndex = e.target.value
                this.ruleForm.shebeiName = this.shebeiOptions[this.shebeiIndex].shebeiName
                this.ruleForm.shebeiId = this.shebeiOptions[this.shebeiIndex].id
            },
            kehuChange(e) {
                this.kehuIndex = e.target.value
                this.ruleForm.kehuName = this.kehuOptions[this.kehuIndex].kehuName
                this.ruleForm.kehuId = this.kehuOptions[this.kehuIndex].id
            },
            yonghuChange(e) {
                this.yonghuIndex = e.target.value
                this.ruleForm.yonghuName = this.yonghuOptions[this.yonghuIndex].yonghuName
                this.ruleForm.yonghuId = this.yonghuOptions[this.yonghuIndex].id
            },
            // 下拉变化
            shebeiOrderTypesChange(e) {
                this.shebeiOrderTypesIndex = e.target.value
                this.ruleForm.shebeiOrderValue = this.shebeiOrderTypesOptions[this.shebeiOrderTypesIndex].indexName
                this.ruleForm.shebeiOrderTypes = this.shebeiOrderTypesOptions[this.shebeiOrderTypesIndex].codeIndex
            },
            // styleChange() {
            // 	this.$nextTick(() => {
            // 		// document.querySelectorAll('.app-update-pv .cu-form-group .uni-yaoxianStyle-yaoxianStyle').forEach(el=>{
            // 		//   el.style.backgroundColor = this.addUpdateForm.yaoxianStyle.content.backgroundColor
            // 		// })
            // 	})
            // },
			// 日期控件
			insertTimeConfirm(val) {
                this.ruleForm.insertTime = val.result;
                this.$forceUpdate();
            },
			// 日期控件
			createTimeConfirm(val) {
                this.ruleForm.createTime = val.result;
                this.$forceUpdate();
            },

            getUUID() {
                return new Date().getTime();
            },
            async onSubmitTap() {
				if ((!this.ruleForm.shebeiOrderUuidNumber)) {
                    this.$utils.msg(`订单编号不能为空`);
                    return
                }
				if ((!this.ruleForm.buyNumber) && (!this.$validate.isIntNumer(this.ruleForm.buyNumber)) && this.ruleForm.buyNumber >0) {
                    this.$utils.msg(`购买数量不能为空，不能小于0 格式为数字`);
                    return
                }
                if (this.ruleForm.id) {
                    await this.$api.update(`shebeiOrder`, this.ruleForm);
                } else {
                    await this.$api.save(`shebeiOrder`, this.ruleForm);
                }
                uni.setStorageSync('pingluenStateState', true);
                this.$utils.msgBack('提交成功');
            },
            getDate(type) {
                const date = new Date();
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();
                if (type === 'start') {
                    year = year - 60;
                } else if (type === 'end') {
                    year = year + 2;
                }
                month = month > 9 ? month : '0' + month;;
                day = day > 9 ? day : '0' + day;
                return `${year}-${month}-${day}`;
            },
            toggleTab(str) {
                this.$refs[str].show();
            }
        }
    }
</script>
<style lang="scss" scoped>
    .container {
        padding: 20upx;
    }

    .content:after {
        position: fixed;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0;
        content: '';
        background-attachment: fixed;
        background-size: cover;
        background-position: center;
    }

    textarea {
        border: 1upx solid #EEEEEE;
        border-radius: 20upx;
        padding: 20upx;
    }

    .title {
        width: 180upx;
    }

    .avator {
        width: 150upx;
        height: 60upx;
    }

    .right-input {
        flex: 1;
        text-align: left;
        padding: 0 24upx;
    }

    .cu-form-group.active {
        justify-content: space-between;
    }

    .cu-form-group .title {
        height: auto;
        line-height:30rpx
    }

    .btn {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-wrap: wrap;
        padding: 20upx 0;
    }

    .cu-form-group {
        padding: 0 24upx;
        background-color: transparent;
        min-height: inherit;
    }

    .cu-form-group+.cu-form-group {
        border: 0;
    }

    .cu-form-group uni-input {
        padding: 0 30upx;
    }

    .uni-input {
        padding: 0 30upx;
    }

    .cu-form-group uni-textarea {
        padding: 30upx;
        margin: 0;
    }

    .cu-form-group uni-picker::after {
        line-height: 80rpx;
    }

    .select .uni-input {
        line-height: 80rpx;
    }

    .input .right-input {
        line-height: 88rpx;
    }
</style>