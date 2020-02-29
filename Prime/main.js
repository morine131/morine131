//1 より大きい自然数で、正の約数が 1 と自分自身のみである数字

const app = new Vue({
    el: '#app',
    data: {
        checkNumber: 0,
        check:"素数です",
        primeCount: 1,
        
        
        
       
    },
    methods:{
        //入力された数字が素数かチェック 
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
        },
    },
    computed:{
        //9999までの素数配列
        primeList: function(){
            let list = {
                list:[2],
                count:1
            }
            let primeFlag = true; 
            //割られる数(i)を10000まで繰り返す
            for(let i = 3; i<10000;i++){
                //primeFlagの初期化
                primeFlag = true;
                //割る数(n)をiの半分+1まで繰り返す(+1しないと4が入っちゃう)
                for(let n = 2;n<i/2+1;n++){
                    //iがnで割り切れた時はフラグをfalseにしてnループを抜ける(次のiループに入る)
                    if(i % n === 0){
                        primeFlag = false;
                        break;
                    }
                }
                //nループで割り切れなかった時に素数リストに加える
               if(primeFlag){
                   list.list.push(i);
                   list.count++;
               }
            }
            
            return list;
        },
        primeList2: function(){
            let list = [];
            let count = 0;
            for(let p = 0 ; p < this.primeList.length;p++){
             let list10 = [];
             for(let i=0;i<10;i++){
                list10.push(this.primeList[count * 10 + i]);
            }
            count++;
            list.push(list10);
        }
          return list;
        },
       

    },
    //watch項目：入力値が変わるたびに素数判定
    watch: {
        checkNumber: function(){
            this.checkPrime()
        }
    },
    //Vueインスタンスが作られた時に、素数判定
     created: function(){
         this.checkPrime();
     }

})

