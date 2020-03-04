package boot.controllers;


import boot.dao.BannerRepository;
import boot.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController
{
    /*Вводится (inject) из application.properties, считается устаревшим,
     исп. Configuration Properties*/
    @Value("${welcome.message}")
    private String message;

    @Autowired
    private BannerRepository bannerRepository;

    private List<Banner> banners = new ArrayList<>();

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        banners.clear();
        banners.addAll(bannerRepository.findBy());
        /*Атрибут (attribute) объекта  org.springframework.ui.Model, или
        объекта​​​​​​​ HttpServletRequest является переменной  Thymeleaf.
        Данная переменная может быть использована везде в  Template.
        В методе addAttribute первый аргумент - имя переменной,
        по которому мы будем обращаться к ней в шаблоне, второй - значение */
        model.addAttribute("message", message);
        model.addAttribute("banners", banners);
        //Контроллёр направляет клиента отправившего запрос на шаблонный файл index.html
        return "index";
    }

    @RequestMapping(value = {"/addBanner" }, method = RequestMethod.GET)
    public String personList(Model model) {

        model.addAttribute("message", "Add Banner");

        Banner banner = new Banner();
        model.addAttribute("banner", banner);

        return "addBanner";
    }

    @RequestMapping(value = { "/addBanner" }, method = RequestMethod.POST)
    public String saveBanner(Model model, //
                             /*Обращение к переменной(атрибуту) Thymleaf */
                             @ModelAttribute("banner") Banner banner) {
        Integer width = banner.getWidth();
        String imgSrc = banner.getImgSrc();

        if (width != null && imgSrc != null && imgSrc.length() > 0) {
            Banner newBanner = new Banner(imgSrc, width, 200, "TEST", "ru" );
            bannerRepository.save(newBanner);
            return "redirect:/index";
        }

        model.addAttribute("message", "Add Banner(Bad Enter!)");
        return "addBanner";
    }
}
