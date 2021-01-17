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
            <pop-bottom :dateClick="clickDate" :infoList="dateInfoList"></pop-bottom>
        </uni-popup>
        <ourLoading :active='isActive' text="loading..." />
    </view>
</template>

<script>
import uniCalendar from '@dcloudio/uni-ui/lib/uni-calendar/uni-calendar.vue';
import uniPopup from "@dcloudio/uni-ui/lib/uni-popup/uni-popup.vue";
import uniFab from '@dcloudio/uni-ui/lib/uni-fab/uni-fab.vue';
import PopBottom from './components/PopBottom.vue';
import ourLoading from '@/components/our-loading/our-loading.vue'
export default {
    components:{
        uniCalendar,
        uniPopup,
        uniFab,
        PopBottom
    },
    onLoad(){
        this.getPersonInfo();
    },
    data() {
        return {
            select:[],
            isActive:true,
            clickDate:'',
            pattern:{
                color: '#5383EC',
                buttonColor: '#5383EC',
            },
            dateArray:[],
            personSchedule:[],
            companySchedule:[],
            scheduleList:[],
            dateInfoList:[],
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
            this.dateInfoList = [];
            if (JSON.stringify(e.extraInfo) !== '{}') {
                this.$refs.popup.open();
                this.scheduleList.forEach(element => {
                    const timeArray = element.startTime.split(' ');
                    const timeOne = timeArray[0];
                    if (timeOne === e.fulldate) {
                        this.dateInfoList.push(element);
                    }
                });
                // console.log(this.dateInfoList);
            }
            this.clickDate=e.fulldate;
            // console.log(e);
        },
        async getSelfSchedule(){
            const res = await this.$request({
                url:'/findSelfSchedule',
                method:'get',
            })
            return res.data;
        },
        async getCompanySchedule(){
            const res = await this.$request({
                url:'/findCompanySchedule',
                method:'get',
                // throttle:true,
            })
            return res.data;
        },
        getPersonInfo () {
            this.getSelfSchedule()
            .then(res=>{
                this.personSchedule = res.object;
                if (this.personSchedule.length !==0 ) {
                    this.personSchedule.forEach(element => {
                        element.type = '个人日程';
                        this.scheduleList.push(element);
                        const timeArray = element.startTime.split(' ');
                        this.dateArray.push(timeArray[0]);
                    });
                }
                this.getCompanyInfo();
            })
            .catch(err=>{
                console.log(err);
                wx.showToast({
                    title:'获取失败',
                    icon:'none',
                    duration: 2500
                });
            })
        },
        getCompanyInfo () {
            this.getCompanySchedule()
            .then(res=>{
                this.companySchedule = res.object;
                if (this.companySchedule.length !==0 ) {
                    this.companySchedule.forEach(element => {
                        element.type = '公司日程';
                        this.scheduleList.push(element);
                        const timeArray = element.startTime.split(' ');
                        this.dateArray.push(timeArray[0]);
                    });
                }
                const resarray = this.getCount(this.dateArray);
                resarray.forEach(element => {
                    this.select.push({
                        date:element.el,
                        info:`${element.count}项日程`
                    }) 
                });
                this.isActive = true;
            })
            .catch(err=>{
                console.log(err);
                wx.showToast({
                    title:'获取失败',
                    icon:'none',
                    duration: 2500
                });
            })
        },
        trigger(e) {
            // console.log(e);
            uni.navigateTo({
                url:`/pages/Schedule/children/scheduleAdd?id=${e.index}`
            });
        },
        getCount(arr){ 
            var obj = {}, k, arr1 = [];
            for (var i = 0, len = arr.length; i < len; i++) {
                k = arr[i];
                if (obj[k]) 
                    obj[k]++;
                else 
                    obj[k] = 1;
            }
            //保存结果{el-'元素'，count-出现次数}
            for (var o in obj) {
                arr1.push({el: o, count: obj[o]});
            }
            return arr1.slice(0);
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