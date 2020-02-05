Vue.component('monster-comp',{
    props:['monster'],

    template: '<div>{{monster.name}} HP:{{monster.hp}} <button @click="doAttack">攻撃する</button></div>',

    methods:{
        doAttack: function(){
            this.$emit('attack',this.monster)
        }
    }
})

Vue.component('item-shop',{
    props:['item'],

    template:   '<div>{{item.name}} :{{item.price}}G <button @click="buyItem">買う</button></div>',

    methods: {
        buyItem: function(){
            this.$emit('buy',this.item)  
        }
    }
})

Vue.component('my-items',{
    props: ['myitem'],
    template: '<div>{{myitem.name}}</div>'
})

new Vue({
    el:'#app',
    data:{
        monsterList:[
            {id:1,name:"スライム",hp:20},
            {id:2,name:"ゴブリン",hp:35},
            {id:3,name:"ドラゴン",hp:200}
        ],
        shopList:[
            {id:4,name:"薬草",price:20},
            {id:5,name:"毒消し",price:30},
            {id:6,name:"手榴弾",price:50}
        ],
        myItems:[{id:7,name:"えりくさ",price:100}],
        myMoney: 100
        
    },
    methods:{
        handleAttack: function(monster){
      
            if(monster.hp > 0 ){
                monster.hp -= 10
            }
        },
        handleBuy: function(item){
            this.myMoney -= item.price
            this.myItems.push(item)
            const itemIndex = this.shopList.indexOf(item)
            this.shopList.splice(itemIndex,1)
        }
    }
})