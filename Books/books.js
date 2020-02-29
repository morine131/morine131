console.log("jsの読み込みOK");

const app = new Vue({
    el:'#app',
    data:{
        search:"",
        bookTitle:"",
        bookSubTitle:"",
        bookImage:"",
        bookAuthors:"",
        convertedObj:null,
        Item:null,
    },
    methods:{
        fetchAPI:function(){
            fetch(`https://www.googleapis.com/books/v1/volumes?q=${this.search}&maxResults=1`)
                .then(response => {
                    console.log(response.status);
                    if(!response.ok){
                        console.error("エラーレスポンス",response);
                    }else{
                        return response.json().then(bookInfo =>{
                                this.trimJSON(bookInfo);
                                this.bookTitle = this.Item.title;
                                this.bookImage = this.Item.imageLinks.thumbnail;
                                this.bookAuthors = this.Item.authors[0];
                                this.bookSubTitle = this.Item.subtitle;
                        })
                    }
                })
        },
        trimJSON:function(bookInfo){
            const json = JSON.stringify(bookInfo);
            this.convertedObj = JSON.parse(json);
            this.Item = this.convertedObj.items[0].volumeInfo;
        }
    }
})