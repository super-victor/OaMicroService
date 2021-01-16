<template>
    <view class="AddrListSelect">
        
        <!-- <view class="head">
            <uni-search-bar placeholder="搜索" @input="filter()" @cancel="reset()"></uni-search-bar>
        </view> -->
        <!-- <view class="list" :key="i" v-for="i in alphabetList">
           <view class="if" v-if="isInput">
                <uni-list class="headAlphabet">{{i}}</uni-list>
                <uni-list :key="j" v-for="j in name">
                    <view class="if" v-if="j.substr(0,1) ==i || pinyin.getFullChars(j).substr(0,1) ==i">
                        <uni-list-item :title="j" clickable link @click="handleClick(j)"></uni-list-item>
                    </view>
                </uni-list>
           </view>
            <view class="if"  v-if="!isInput">
            </view>
        </view> -->
        <view class="list">
            <uni-indexed-list :options="list" :showSelect="false" ></uni-indexed-list>
        </view>
    </view>
</template>

<script>
import uniSearchBar from "@dcloudio/uni-ui/lib/uni-search-bar/uni-search-bar"
import uniIndexedList from "@dcloudio/uni-ui/lib/uni-indexed-list/uni-indexed-list"
export default {
    components:{
			uniIndexedList,
            uniSearchBar
		},
    data(){
        return{
            pinyin:'',
            isInput:true,
            //Search:'',
            // alphabet:'',
            //name:['Ans','Actor','Adr','Bob','Dg','Gary','Caitlin','Arthas','Catalina','Brand','Eve','Android','C++','陈元华','黄煜城','刘以强','李京鸿']
            list:[{
                "letter": "A",
                "data" :['']
            },{
                "letter": "B",
                "data" :['']
            },{
                "letter": "C",
                "data" :['']
            },{
                "letter": "D",
                "data" :['']
            },{
                "letter": "E",
                "data" :['']
            },{
                "letter": "F",
                "data" :['']
            },{
                "letter": "G",
                "data" :['']
            },{
                "letter": "H",
                "data" :['']
            },{
                "letter": "I",
                "data" :['']
            },{
                "letter": "J",
                "data" :['']
            },{
                "letter": "K",
                "data" :['']
            },{
                "letter": "L",
                "data" :['']
            },{
                "letter": "M",
                "data" :['']
            },{
                "letter": "N",
                "data" :['']
            },{
                "letter": "O",
                "data" :['']
            },{
                "letter": "P",
                "data" :['']
            },{
                "letter": "Q",
                "data" :['']
            },{
                "letter": "R",
                "data" :['']
            },{
                "letter": "S",
                "data" :['']
            },{
                "letter": "T",
                "data" :['']
            },{
                "letter": "U",
                "data" :['']
            },{
                "letter": "V",
                "data" :['']
            },{
                "letter": "W",
                "data" :['']
            },{
                "letter": "X",
                "data" :['']
            },{
                "letter": "Y",
                "data" :['']
            },{
                "letter": "Z",
                "data" :['']
            }]
        }
    },
    created(){
        this.pinyin = require('js-pinyin');
        this.pinyin.setOptions({checkPolyphone: false, charCase: 0});
        // console.log(pinyin.getFullChars('黄煜城 ').substr(0,1));
        // console.log(pinyin.getCamelChars('黄煜城'));
    },
    methods:{
        search(res){
            console.log(res);
        },
        filter() {
           this.isInput = false;
        },
        reset(){
           this.isInput = true;
            console.log("err")
        },
        handleClick(j){
            console.log(j+' isClick')
        },
    }
}
</script>

<style  scoped lang='scss'>
    .AddrListSelect{
        
        .head{
            height:50rpx;
        }
        .list{
            margin-top:400rpx;
        }
    }
</style>