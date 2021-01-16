<template>
    <view class="RoomManage">
        <!-- <view class="content">
		<view class="current_time" @click.stop="openCalendar">{{time}}</view>
		<calendar-picker 
						ref='calendarPicker' 
						:level='level'
						:isLunar='isLunar' 
						:currentTime="currentTime"
						:isShowChinaTime="isShowChinaTime"
						@changecalendar='changeCalendar'
						@confirm='confirmCalendar'
						></calendar-picker> 
 	</view> -->

        <view class="date">
            <uni-calendar 
            :insert="false"
            :lunar="false" 
            @change="change"
            ref="calendar"
            ></uni-calendar> 
            <button @click="open">打开日历</button>      
        </view>
        <uni-grid class="uniGrid" :column="3" :show-border="true"  :square="true">
            <uni-grid-item>
                <text class="text">101会议室</text>
            </uni-grid-item>
            <uni-grid-item>
                <text class="text">202会议室</text>
            </uni-grid-item>
            <uni-grid-item>
                <text class="text">121会议室</text>
            </uni-grid-item>
            <uni-grid-item>
                <text class="text">123会议室</text>
            </uni-grid-item>
            <uni-grid-item>
                <text class="text">321会议室</text>
            </uni-grid-item>
            <uni-grid-item>
                <text class="text">311会议室</text>
            </uni-grid-item>
        </uni-grid>
         <view>
            <uni-fab
                :pattern="pattern"
                :content="content"
                horizontal="left"
                vertical="bottom"
                direction="vertical"
                @trigger="trigger"
            ></uni-fab>
        </view>
    </view> 
    
</template>

<script>
import uniCalendar from '@dcloudio/uni-ui/lib/uni-calendar/uni-calendar'
import uniGrid from "@dcloudio/uni-ui/lib/uni-grid/uni-grid"
import uniGridItem from "@dcloudio/uni-ui/lib/uni-grid-item/uni-grid-item"
	// import CalendarPicker from '../../../components/datePicker/bory-datePicker';
	// import {getDateTime,chinaHours} from '../../../components/datePicker/utils/util.js'
import uniFab from "@dcloudio/uni-ui/lib/uni-fab/uni-fab"
import uniIcons from "@dcloudio/uni-ui/lib/uni-icons/uni-icons"

export default {
    components:{
   //         CalendarPicker,
            uniGrid,
            uniGridItem,
            uniCalendar,
            uniFab,
            uniIcons
		},
    data(){
        return{
         //   	chinaHours,
				// chinaTime: '亥时',
				// isLunar: false,
				// isShowChinaTime:true,
				// time: '请选择时间',
				// currentTime:'',
                // level: '6'
                pattern:{
                    buttonColor:'#8791eb',
                    selectedColor:'#8791eb',
                    color:'#3c3e49'
                },
                content:[{
                    text:'预约会议',
                },{
                    text:'234',
                }]
        }
    },
    onLoad() {
			// this.currentTime = '2020-05-25 15:40:10';
			// if(this.isShowChinaTime){
			// 	if(this.chinaTime.length){
			// 		const { startTime } = chinaHours.find(item=>item.name == this.chinaTime);
			// 		const timeArray = this.currentTime.split(' ');
			// 		this.currentTime = `${timeArray[0]} ${startTime}`;
			// 	}
			// }
			
		},
    created(){
     
    },
    methods:{
        	// //打开日期选择器
			// openCalendar(){
			// 	this.$refs['calendarPicker'].show();
			// },
			// //切换阴阳历
			// changeCalendar(value){
			// 	this.isLunar = value;
			// },
			// //确定选择日期
			// confirmCalendar(value){
			// 	console.log('确认后数据：',value)
			// 	const {date,time,chinaTime,gzYear,IMonthCn,IDayCn,lYear} = value;
			// 	const dateTime = `${date} ${time}`;
			// 	this.currentTime = dateTime;
			// 	if(this.isLunar){
			// 		if(this.isShowChinaTime){
			// 			this.time = `${lYear}年${IMonthCn}${IDayCn} ${chinaTime}`;
			// 		}else{
			// 			this.time = `${lYear}年${IMonthCn}${IDayCn} ${time}`;
			// 		}
					
			// 	}else{
			// 		this.time = dateTime;
			// 	}
			// },
          open(){
            this.$refs.calendar.open();
        },
        trigger(res){
            console.log('trigger',res)
        }
    }
}
</script>

<style  scoped lang='scss'>
    .RoomManage{
        .uniGrid{
            .text{
                text-align: center;
                line-height: 250rpx;
               font-size: 30rpx;
            }
        }
    }
</style>