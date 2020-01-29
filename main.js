//1 より大きい自然数で、正の約数が 1 と自分自身のみである数字

const app = new Vue({
    el: '#app',
    data: {
        checkNumber: 0,
        check:"素数です"
       
    },
    methods:{
        //s素数チェック
        checkPrime: function(){
                if(this.checkNumber<2){
                    this.check="素数ではない";
                    return;
                }else{
                    for (let i = 2;i <= this.checkNumber-1 ; i++){ 
                        if(this.checkNumber % i === 0){
                            this.check="素数ではない";
                         return;
                        }
                    }  
                 }
            this.check="素数です";
        }
    },
    watch: {
        checkNumber: function(){
            this.checkPrime()
        }
    },
     created: function(){
         this.checkPrime();
     }

})

