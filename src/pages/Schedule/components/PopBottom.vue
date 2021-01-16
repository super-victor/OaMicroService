<template>
    <view class="pop_area">
        <text class="popText">{{dateClick}}</text>
        <scroll-view scroll-y="true" class="event">
            <view
            v-for="item in eventsList"
            :key="item.id"
            class="event_item">
                <view class="schedule_row">
                    <image src="../../../static/schedule/time.png"></image>
                    <text>2020-01-22至2020-01-23</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/position.png"></image>
                    <text>地点：师大花园</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/schedule.png"></image>
                    <text>日程：聚餐</text>
                </view>
                <view class="schedule_row">
                    <image src="../../../static/schedule/type.png"></image>
                    <text>类型：个人日程</text>
                </view>
                <view class="event_edit">
                    <button class="button edit" @click="editSchedule(item)">编辑</button>
                    <button class="button delete" @click="deleteSchedule">删除</button>
                </view>
            </view>
        </scroll-view>
    </view>
</template>

<script>
import uniPopup from '@dcloudio/uni-ui/lib/uni-popup/uni-popup';
export default {
    props:['dateClick'],
    components: {
        uniPopup
    },
    data() {
        return {
            eventsList:[{
                id:0
            },{
                id:1
            },{
                id:2
            },{
                id:3
            },{
                id:4
            },{
                id:5
            },{
                id:6
            }]
        }
    },
    methods: {
        editSchedule(item) {
            uni.navigateTo({
                url:`/pages/Schedule/children/scheduleEdit?id=${item.id}`
            });
        },
        close() {

        },
        confirm() {

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
        height: 100rpx;
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