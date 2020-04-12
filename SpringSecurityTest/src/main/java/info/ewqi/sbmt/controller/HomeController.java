package info.ewqi.sbmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Controllerクラスのアノテーション
@Controller
public class HomeController {



 // root(/)にアクセスした際にGetメソッドを実行。
 @GetMapping("/")
 public String goIndex(Model model) {

     //template配下のファイル名を指定することでViewを呼び出せる。
     return "index";
 }

}
