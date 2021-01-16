<template>
    <view>
        <uni-calendar 
        :insert="true"
        :lunar="true" 
        :start-date="'2018-1-1'"
        :end-date="'2100-1-1'"
        :selected="select"
        @change="change"
        @to-click="toClick"
        ></uni-calendar>
        <view class="new_area">
            <uni-fab
            :pattern="pattern"
            :content="content"
            @trigger="trigger"
            style="width:30rpx;height:30rpx"
            ></uni-fab>
        </view>
        <uni-popup ref="popup" type="bottom">
            <pop-bottom :dateClick="clickDate"></pop-bottom>
        </uni-popup>
    </view>
</template>

<script>
import uniCalendar from '@dcloudio/uni-ui/lib/uni-calendar/uni-calendar.vue';
import uniPopup from "@dcloudio/uni-ui/lib/uni-popup/uni-popup.vue";
import uniFab from '@dcloudio/uni-ui/lib/uni-fab/uni-fab.vue';
import PopBottom from './components/PopBottom.vue';
export default {
    components:{
        uniCalendar,
        uniPopup,
        uniFab,
        PopBottom
    },
    data() {
        return {
            select:[{
                date:'2021-01-09',
                info:'3项日程',
            },{
                date:'2021-01-10'
            }],
            clickDate:'',
            pattern:{
                color: '#5383EC',
                buttonColor: '#5383EC',
            },
             content: [
                {
                    text: '个人',
                    iconPath:'/static/schedule/schedule_person.png',
                    selectedIconPath: '/static/schedule/schedule_person.png',
                    active: false
                },
                {
                    text: '公司',
                    iconPath:'/static/schedule/schedule_company.png',
                    selectedIconPath: '/static/schedule/schedule_company.png',
                    active: false
                }
            ]
        }
    },
    methods: {
        change(e) {
            if (JSON.stringify(e.extraInfo) !== '{}') {
                this.$refs.popup.open();
            }
            this.clickDate=e.fulldate;
            console.log(e);
        },
        trigger(e) {
            console.log(e);
            uni.navigateTo({
                url:`/pages/Schedule/children/scheduleAdd?id=${e.index}`
            });
        }
    }
}
</script>

<style lang="scss" scoped>
.new_area {
    height: 30rpx;
}
::v-deep .uni-fab__circle {
    width: 30rpx;
    height: 30rpx;
}
::v-deep .uni-fab__content {
    width: 30rpx;
    height: 30rpx;
}
</style>