<template>
	<view class="calendar" v-if="visible">
		<view class="calendar-mask" @click.stop='maskEvent'></view>
		<view class="calendar-container" @click.stop='handleClickOutSide'>
			<view class="calendar-header" :style="{backgroundColor: headerBgColor}">
				<view class="txt solar" :style="{color: (isLunar ?  defaultColor : activeColor)}" @click="changeCalendar(0)">
					{{solarText}}
				</view>
				<view class="txt lunar" :style="{color: (!isLunar ?  defaultColor : activeColor)}" @click="changeCalendar(1)">
					{{lunarText}}
				</view>
				<view class="txt confirm" :style="{color: confirmColor}" @click="changeCalendar(2)">
					{{confirmText}}
				</view>
			</view>
			<view class="calendar-body">
				<picker-view @change="timeChange" data-index='0' data-id='year' :value='[multiIndex[0]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level > 0 ">
					<picker-view-column>
						<view v-for="item in yearData" :key='item' style="line-height: 40px;text-align:center;">{{item}}</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='1' data-id='month' :value='[multiIndex[1]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level> 1">
					<picker-view-column>
						<view v-for="item in monthData" :key='item' style="line-height: 40px;text-align:center;">{{item}}</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='2' data-id='day' :value='[multiIndex[2]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level > 2 ">
					<picker-view-column>
						<view v-for="item in dayData" :key='item' style="line-height: 40px;text-align:center;">{{item}}</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='3' data-id='hour' :value='[multiIndex[3]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level > 3 && (!isLunar || isLunar && !isShowChinaTime)">
					<picker-view-column>
						<view v-for="item in hours" :key='item' style="line-height: 40px;text-align:center;">{{item}}时</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='4' data-id='minus' :value='[multiIndex[4]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level > 4 && (!isLunar || isLunar && !isShowChinaTime)">
					<picker-view-column>
						<view v-for="item in minutes" :key='item' style="line-height: 40px;text-align:center;">{{item}}分</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='5' data-id='second' :value='[multiIndex[5]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="level > 5 && (!isLunar || isLunar && !isShowChinaTime)">
					<picker-view-column>
						<view v-for="item in seconds" :key='item' style="line-height: 40px;text-align:center;">{{item}}秒</view>
					</picker-view-column>
				</picker-view>
				<picker-view @change="timeChange" data-index='6' data-id='china' :value='[multiIndex[6]]' indicator-style="height: 40px;"
				 class='calendar-body-wrap' v-if="isLunar && isShowChinaTime">
					<picker-view-column>
						<view v-for="item in chinaHours" :key='item.startTime' style="line-height: 40px;text-align:center;">{{item.name}}</view>
					</picker-view-column>
				</picker-view>
			</view>
		</view>
	</view>
</template>

<script>
	import clickoutside from '../directives/clickoutside.js';
	import {
		calendar
	} from '../libs/calendar.js'
	import {
		getYears,
		getMonths,
		getDays,
		getHours,
		getMinutes,
		getSeconds,
		getDateTime,
		formateTime,
		addZero,
		createArray,
		getDateStringTime,
		chinaHours,
		getChinaTime
	} from '../utils/util.js';
	export default {
		name: 'CalendarPicker',
		props: {
			level: {
				type: String,
				default: '5'
			},
			isShowChinaTime: {
				type: Boolean,
				default: false
			},
			currentTime: {
				type: String,
				default: ''
			},
			startYear: {
				type: Number,
				default: 1900
			},
			endYear: {
				type: Number,
				default: 2100
			},
			isLunar: {
				type: Boolean,
				default: false
			},
			headerBgColor: {
				type: String,
				default: '#e9e9e9'
			},
			solarText: {
				type: String,
				default: '阳历'
			},
			lunarText: {
				type: String,
				default: '阴历'
			},
			activeColor: {
				type: String,
				default: '#912222'
			},
			defaultColor: {
				type: String,
				default: '#000000'
			},
			confirmText: {
				type: String,
				default: '确定'
			},
			confirmColor: {
				type: String,
				default: '#912222'
			}
		},
		computed:{
			yearData(){
				return this.isLunar ? this.ganZhiYears : this.years;
			},
			monthData(){
				return this.isLunar ? this.ganZhiMonths : this.months;
			},
			dayData(){
				return this.isLunar ? this.ganZhiDays : this.days;
			}
		},
		watch: {
			currentTime: {
				handler(value) {
					if (value) {
						this.years = getYears(this.startYear, this.endYear);
						this.months = getMonths();
						this.hours = getHours();
						this.minutes = getMinutes();
						this.seconds = getSeconds();
						const selectTime = value.split(' ')[1];
						const timeArray = selectTime.split(':');
						const dateArray = value.split(' ')[0].split('-');
						this.days = getDays(dateArray[0], dateArray[1]);
						const dateTimeArray = dateArray.concat(timeArray);
						this.$nextTick(() => {
							this.selectYear = dateArray[0];
							this.selectMonth = dateArray[1];
							this.selectDay = dateArray[2];
							const names = ['year', 'month', 'day', 'hour', 'minute', 'second', 'china'];
							let multiIndex = [];
							names.forEach((name, i) => {
								if (name == 'china') {
									multiIndex.push(0);
								} else {
									const index = this[name + 's'].findIndex(item => item == dateTimeArray[i]);
									multiIndex.push(index);
								}
								this.multiIndex = multiIndex;
							})
							if (this.isLunar) {
								if (this.isShowChinaTime) {
									const time =
										`${this.hours[this.multiIndex[3]]}:${this.minutes[this.multiIndex[4]]}:${this.seconds[this.multiIndex[5]]}`;
									const {
										name
									} = getChinaTime(time);

									const index = this.chinaHours.findIndex(item => item.name == name);
									this.multiIndex.splice(this.multiIndex.length - 1, 1, index)
								}

								this.getLunarDate()
							}

						})


					}
				},
				immediate: true
			}
		},
		data() {
			return {
				chinaHours,
				multiIndex: [],
				selectYear: '',
				selectMonth: '',
				selectDay: '',
				selectTime: '',
				isLeap: false,
				visible: false,
				years: [],
				months: [],
				days: [],
				hours: [],
				minutes: [],
				seconds: [],
				ganZhiYears: [],
				ganZhiMonths: [],
				ganZhiDays: []
			}
		},
		methods: {
			handleClickOutSide() {
				return;
			},
			maskEvent() {
				this.hide()
			},
			show() {
				this.visible = true;
			},
			hide() {
				this.visible = false;
			},
			getLunarDate() {
				const lunarData = calendar.solar2lunar(this.selectYear, this.selectMonth, this.selectDay);
				console.log('lunarData:', lunarData);
				const {
					lYear,
					lMonth,
					lDay,
					isLeap,
					IMonthCn,
					IDayCn
				} = lunarData;
				this.selectYear = lYear;
				this.selectMonth = lMonth;
				this.selectDay = lDay;
				this.isLeap = isLeap;
				this.ganZhiYears = this.years.map(year => `${year}${calendar.getGanZhiYear(year)}`);
				const yearIndex = this.ganZhiYears.findIndex(item => item.indexOf(lYear) > -1);
				this.multiIndex.splice(0, 1, yearIndex);
				const leapMonth = calendar.leapMonth(lYear);
				console.log('leapMonth:', leapMonth)
				this.ganZhiMonths = this.months.map(month => calendar.toChinaMonth(month));
				let monthCount;
				if (leapMonth > 0) { //所选的年有闰年
					this.ganZhiMonths.splice(leapMonth, 0, `闰${calendar.toChinaMonth(leapMonth)}`);
					monthCount = calendar.leapDays(lYear);
				} else {
					monthCount = calendar.monthDays(lYear, lMonth)
				}
				const monthIndex = this.ganZhiMonths.findIndex(item => item == IMonthCn);
				this.multiIndex.splice(1, 1, monthIndex);

				const ganZhiDays = [];
				for (let i = 1; i <= monthCount; i++) {
					ganZhiDays.push(calendar.toChinaDay(i));
				}
				this.ganZhiDays = ganZhiDays;
				const dayIndex = this.ganZhiDays.findIndex(item => item == IDayCn);
				this.multiIndex.splice(2, 1, dayIndex);
				if (this.isShowChinaTime) {
					const time =
						`${this.hours[this.multiIndex[3]]}:${this.minutes[this.multiIndex[4]]}:${this.seconds[this.multiIndex[5]]}`;
					const {
						name
					} = getChinaTime(time);
				
					const index = this.chinaHours.findIndex(item => item.name == name);
					this.multiIndex.splice(this.multiIndex.length - 1, 1, index)
				}
				
			},
			getSolarDate() {
				const solarData = calendar.lunar2solar(this.selectYear, this.selectMonth, this.selectDay, this.isLeap);
				console.log('solarData:', solarData);
				const {
					cYear,
					cMonth,
					cDay,
					lYear,
					lMonth,
					lDay,
					isLeap,
					IMonthCn,
					IDayCn
				} = solarData;
				this.selectYear = cYear;
				this.selectMonth = cMonth;
				this.selectDay = cDay;
				this.isLeap = isLeap;
				const yearIndex = this.years.findIndex(item => item == cYear);
				const monthIndex = this.months.findIndex(item => item == cMonth);
				const dayIndex = this.days.findIndex(item => item == cDay);
				this.multiIndex.splice(0, 1, yearIndex);
				this.multiIndex.splice(1, 1, monthIndex);
				this.multiIndex.splice(2, 1, dayIndex);
			},
			timeChange(e) {
				const {
					id,
					index
				} = e.target.dataset;
				// console.log('++++++',id,index)
				const scrollIndex = e.detail.value[0];
				this.multiIndex.splice(index, 1, scrollIndex);
				const names = ['Year', 'Month', 'Day'];
				if (!this.isLunar) {
					if (index == 1) {
						this.selectMonth = addZero(scrollIndex + 1) + '';
						this.days = getDays(this.selectYear, this.selectMonth);
						if (this.multiIndex[index + 1] + 1 > this.days.length) {
							this.multiIndex.splice(index + 1, 1, this.days.length - 1);
							this.selectDay = addZero(this.days.length - 1);
						}
					} else if (index == 0) {
						this.selectYear = this.years[scrollIndex];
					} else {
						this['select' + names[index]] = addZero(scrollIndex + 1) + '';
					}
					this.selectTime = `${addZero(this.multiIndex[3])}:${addZero(this.multiIndex[4])}:${addZero(this.multiIndex[5])}`;
					this.$nextTick(() => {
						this.selectDate = `${this.selectYear}-${this.selectMonth}-${this.selectDay} ${this.selectTime}`;
						// console.log('selectDate', this.selectTime)
					})
				} else {
					if (index == 0) {
						this.selectYear = Number(this.ganZhiYears[this.multiIndex[0]].slice(0, 4));
						const leapMonth = calendar.leapMonth(this.selectYear);
						console.log('leapMonth:', leapMonth)
						this.ganZhiMonths = this.months.map(month => calendar.toChinaMonth(month));
						let monthCount, monthIndex;
						if (leapMonth > 0) { //所选的年有闰年
							this.ganZhiMonths.splice(leapMonth, 0, `闰${calendar.toChinaMonth(leapMonth)}`);
						}
						if (this.multiIndex[1] + 1 > this.ganZhiMonths.length) {
							this.multiIndex.splice(1, 1, this.ganZhiMonths.length - 1);
						}
						const month = this.ganZhiMonths[this.multiIndex[1]];
						monthIndex = this.ganZhiMonths.findIndex(item => item == month);
						if (leapMonth > 0) {
							console.log('======', )
							if (monthIndex == leapMonth) {
								this.selectMonth = monthIndex;
								this.isLeap = true;
								monthCount = calendar.leapDays(this.selectYear);
							} else {
								if (monthIndex > leapMonth) {
									this.selectMonth = monthIndex
								} else {
									this.selectMonth = monthIndex + 1;
								}
								this.isLeap = false;
								monthCount = calendar.monthDays(this.selectYear, this.selectMonth)
							}
						} else {
							this.selectMonth = monthIndex + 1;
							this.isLeap = false;
							monthCount = calendar.monthDays(this.selectYear, this.selectMonth)
						}
						const ganZhiDays = [];
						for (let i = 1; i <= monthCount; i++) {
							ganZhiDays.push(calendar.toChinaDay(i));
						}
						this.ganZhiDays = ganZhiDays;
						if (this.multiIndex[2] + 1 > this.ganZhiDays.length) {
							this.multiIndex.splice(2, 1, this.ganZhiDays.length - 1);
						}
					} else if (index == 1) {
						const leapMonth = calendar.leapMonth(this.selectYear);
						console.log('leapMonth:', leapMonth)
						let monthCount, monthIndex;
						const month = this.ganZhiMonths[this.multiIndex[1]];
						monthIndex = this.ganZhiMonths.findIndex(item => item == month);
						if (leapMonth > 0) {
							if (monthIndex == leapMonth) {
								this.selectMonth = monthIndex;
								this.isLeap = true;
								monthCount = calendar.leapDays(this.selectYear);
							} else {
								if (monthIndex > leapMonth) {
									this.selectMonth = monthIndex
								} else {
									this.selectMonth = monthIndex + 1;
								}
								this.isLeap = false;
								monthCount = calendar.monthDays(this.selectYear, this.selectMonth)
							}
						} else {
							this.selectMonth = monthIndex + 1;
							this.isLeap = false;
							monthCount = calendar.monthDays(this.selectYear, this.selectMonth)
						}
						const ganZhiDays = [];
						for (let i = 1; i <= monthCount; i++) {
							ganZhiDays.push(calendar.toChinaDay(i));
						}
						this.ganZhiDays = ganZhiDays;
						if (this.multiIndex[2] + 1 > this.ganZhiDays.length) {
							this.multiIndex.splice(2, 1, this.ganZhiDays.length - 1);
						}
					} else if (index == 2) {
						this.selectDay = this.multiIndex[2] + 1;
					}else if(index == 6){
						const { startTime } = this.chinaHours[scrollIndex];
						this.selectTime = startTime;
					}
				}
			},

			changeCalendar(index) {
				if (index == 0) { //阳历
					this.$emit('changecalendar', false);
					this.getSolarDate();
				} else if (index == 1) { //阴历
					this.$emit('changecalendar', true);
					this.getLunarDate()
				} else if (index == 2) { //确定
					let selectDate;
					if (this.isLunar) {
						selectDate = calendar.lunar2solar(this.selectYear, this.selectMonth, this.selectDay, this.isLeap);
					} else {
						selectDate = calendar.solar2lunar(this.selectYear, this.selectMonth, this.selectDay);
					}
					this.hide();
					selectDate['date'] = getDateStringTime(selectDate['date']);
					selectDate['lunarDate'] = getDateStringTime(selectDate['lunarDate']);
					let time = this.selectTime;
					if(this.isShowChinaTime){
						const { name } = this.chinaHours[this.multiIndex[6]];
						selectDate['chinaTime'] = name;
					}else{
						selectDate['chinaTime'] = getChinaTime(time) && getChinaTime(time)['name'];
					}
					// console.log(time);
					this.$emit('confirm', { ...selectDate,
						time
					})
				}

			}
		},
		mounted() {

		}

	}
</script>

<style lang="scss" scoped>
	$zIndex: 1000;

	.calendar {
		.calendar-mask {
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-color: rgba(0, 0, 0, .5);
			z-index: $zIndex;
		}

		.calendar-container {
			position: absolute;
			bottom: 0;
			left: 0;
			right: 0;
			height: 500rpx;
			background-color: #fff;
			z-index: $zIndex+1;
			display: flex;
			flex-direction: column;

			.calendar-header {
				display: flex;
				justify-content: space-between;
				align-items: center;
				height: 80rpx;
				box-sizing: border-box;
				padding: 0 32rpx;

				.txt {
					flex: 1;
					font-size: 32rpx;

					&.solar {
						text-align: left;
					}

					&.lunar {
						text-align: center;
					}

					&.confirm {
						text-align: right;
					}
				}
			}

			.calendar-body {
				flex: 1;
				display: flex;

				.calendar-body-wrap {
					flex: 1;
					font-size: 26rpx;
				}
			}
		}
	}
</style>
