const vue = new Vue({
    el:'#app',
    data:{
        //入力された数字の文字列
        inputNumberString　: "",
        //表示される数
        displayNumber :0,
        //演算子
        operator: "" ,
        //○る数を入力中かどうかの判定
        calculating: false,
        //○○られる数
        rareruNumber: 0,
        //○る数
        ruNumber: 0,
        //○数を初期化するかの判定
        ruNumber_firstInput_Flag : true,
    },
    methods:{
       
        clickNumber: function(num){
            //演算子が押された後＝○る数の入力中かどうかの判定
            if(this.calculating){
                //ru
                if(this.ruNumber_firstInput_Flag){
                    this.displayNumber = 0;
                    this.inputNumberString = "";
                    this.ruNumber_firstInput_Flag = false;
                }
                this.inputNumberString += num;
                this.displayNumber = Number(this.inputNumberString);
                this.ruNumber =this.displayNumber;
            }else{
            this.inputNumberString += num;
             //数字変換する
            this.displayNumber = Number(this.inputNumberString);
            this.rareruNumber =this.displayNumber;
           }
        },
        clickOperator: function(ope){
            if(this.calculating){
                this.calculate();
            }
            this.operator = ope ;
            this.calculating = true;
            this.inputNumberString = "";
        },
        calculate: function(){
            switch(this.operator){
                case '+' :
                    this.displayNumber = this.rareruNumber + this.ruNumber;
                    break;

                case '-' :
                    this.displayNumber = this.rareruNumber - this.ruNumber;
                    break;

                case '×' :
                    this.displayNumber = this.rareruNumber * this.ruNumber;
                    break;

                case '/' :
                    if(this.ruNumber === 0){
                        this.displayNumber = "0での除算は行えません"
                        setTimeout(this.clear, 2000);
                        break;
                    }else{
                          this.displayNumber = this.rareruNumber / this.ruNumber;
                          break;
                        }
            }
            this.rareruNumber = this.displayNumber;
            this.calculating = false;
            this.ruNumber = 0;
            this.inputNumberString = "";
            this.operator = "";
        },
        clear: function(){
        this.inputNumberString　= "";
        this.displayNumber =0;
        this.operator= "" ;
        this.calculating= false;
        this.rareruNumber= 0;
        this.ruNumber= 0;
        this.ruNumber_firstInput_Flag =true;
        }
    }
})