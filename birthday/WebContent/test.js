/**
 *
 */

new Vue({
	el: "#app",
	data: {
		birthYear: 1995,
		birthMonth: 1,
		birthDay: 31,
		etoList:["子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"]

	},
	methods:{

	},
	computed:{
		eto: function(){ //干支判定算出プロパティ
			let etoAnimal = "";
			let etoIndex = 0;
			if(this.birthYear>=1996){
				etoIndex = (this.birthYear - 1996) % 12;
			}else{
				etoIndex = 12 - ((1996-this.birthYear) % 12);
			}


			etoAnimal = this.etoList[etoIndex];
			return etoAnimal;
		},
		date: function(){
			const dateObj = new Date(this.birthYear,this.birthMonth - 1,this.birthDay)
			return dateObj;
		},
		youbi: function(){
			return this.date.getDay();

		}

	}

})