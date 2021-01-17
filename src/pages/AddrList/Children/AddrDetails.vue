<template>
  <view class="AddrDetails">
    <uni-card class="uCard" :title="peopleName"  :extra="'职工id：'+personalMessage[0].employeeId"  :is-full="true" >
        <view class="mes">{{"电话号码："+ personalMessage[0].phone}}</view>
        <view class="mes">{{"电子邮箱："+ personalMessage[0].email}}</view>
        <view class="mes">{{"入职日期："+ (personalMessage[0] && personalMessage[0].entryTime.substr(0,10))}}</view>
        <!-- <template v-slot:footer>
        <view class="footer-box">
         / <button class="btn" type="warn" @tap="handleClick()">删除此联系人</button>
        </view>
    </template> -->
    </uni-card>
      <ourLoading :active='isActive' text="loading..." />
   </view>
</template>

<script>
import uniCard from "@dcloudio/uni-ui/lib/uni-card/uni-card"
import ourLoading from '@/components/our-loading/our-loading.vue'
export default {
    components:{
      uniCard,ourLoading
    },
    data(){
      return{
        peopleName:'',
        datalist:[],
        personalMessage:[],
        isActive:true,
        
      }
    },
    onLoad(res){
       this.peopleName = res.name,
       this.dataRequest().then(res=>{
         this.datalist = res.object;
         this.isActive = false
         this.personalMessage = this.datalist.filter(item=>{
           return item.name == this.peopleName?item:''
         })
         console.log(this.personalMessage[0].name)
       })
    },
    methods:{
          async dataRequest(){
            const res = await this.$request({
            url:'/addressbook',
            method:'get',
            })
            return res.data;
        },
    }
}
</script>

<style scoped lang="scss">
    .AddrDetails{
        .uCard{
            .mes{
              font-size:30rpx;
              margin-bottom:20rpx;
            }
            .btn{
              height:80rpx;
              font-size:30rpx;
            }
        }
    }
</style>