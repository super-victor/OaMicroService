<template>
    <view class="pop_area">
        <text class="popText">{{dateClick}}</text>
        <scroll-view scroll-y="true" class="event">
            <view
            v-for="item in infoList"
            :key="item.scheduleId"
            class="event_item">
                <view class="schedule_row">
                    <image src="../../../static/schedule/time.png"></image>
                    <text>{{ item.startTime }}至{{item.endTime}}</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/position.png"></image>
                    <text>地点：{{ item.location }}</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/schedule.png"></image>
                    <text>日程：{{ item.content }}</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/type.png"></image>
                    <text>类型：{{ item.type }}</text>
                </view>
                <view class="event_edit">
                    <button class="button edit" @tap="editSchedule(item)">编辑</button>
                    <button class="button delete" @tap="deleteSchedule(item.scheduleId)">删除</button>
                </view>
            </view>
        </scroll-view>
    </view>
</template>

<script>
import uniPopup from '@dcloudio/uni-ui/lib/uni-popup/uni-popup';
export default {
    props:['dateClick','infoList'],
    components: {
        uniPopup
    },
    data() {
        return {
            us:'a'
        }
    },
    methods: {
        editSchedule(item) {
            // console.log(item);
            uni.navigateTo({
                url:`/pages/Schedule/children/scheduleEdit?item=${encodeURIComponent(JSON.stringify(item))}`
            });
        },
        async deleteSelfSchedule(data){
            const res = await this.$request({
                url:'/SelfSchedule',
                method:'delete',
                data
            })
            return res.data;
        },
        deleteSchedule(id){
            uni.showModal({
                title: '确认',
                content: '您确认要删除该项日程吗？',
                success: (res) => {
                    if (res.confirm) {
                        this.deleteInfo(id);
                    } else if (res.cancel) {
                        console.log('用户点击取消');
                    }
                }
            })
        },
        deleteInfo(id) {
            this.deleteSelfSchedule({
                scheduleId:id
            })
            .then(res=>{
                if (res.status === 200) {
                    uni.navigateTo({
                        url:'/pages/Schedule/Schedule'
                    })
                }
            })
            .catch(err=>{
                wx.showToast({
                    title:'修改失败',
                    icon:'none',
                    duration: 2500
                });
            })
        }
    }
}
</script>

<style lang="scss" scoped>
.pop_area {
    background-color: #fff;
    height: 800rpx;
    border-radius: 5px 5px 0 0;
    display: flex;
    flex-direction: column;
    .popText {
        height: 50rpx;
        box-shadow:0px 1px 1px #EFEFEE;
        padding: 20rpx;
        color: $uni-text-color;
    }
    .event {
        padding: 20rpx;
        font-size: $uni-font-size-base;
        overflow-y: scroll;
        color: #606266;
        .event_item {
            border: 1rpx dashed #EBEEF5;
            margin-bottom: 40rpx;
            background-color: #F5F7FA;
            border-radius: 2px;
            padding: 20rpx;
            display: flex;
            flex-direction: column;
            .schedule_row {
                image {
                    width: 28rpx;
                    height: 28rpx;
                    margin-right: 5rpx;
                    margin-top: 5rpx;
                }
                height: 50rpx;
                display: flex;
                flex-direction: row;
            }
            .event_edit {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                .button {
                    font-size: $uni-font-size-sm;
                    margin: 20rpx;
                    margin-right: 20rpx;
                }
                .edit {
                    background-color: #9CB7F0;
                }
                .delete {
                    background-color: #F56C6C;
                }
            }
        }
    }
}
</style>