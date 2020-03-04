package boot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**Класс для хранения данных об интернет-баннере.
 @author Артемьев Р.А.
 @version 11.11.2019 */
/*@Entity — Указывает, что данный бин (класс) является сущностью.*/
@Entity
/*@Table — указывает на имя таблицы, которая будет отображаться в этой сущности.*/
@Table(name = "banners")
public class Banner
{
    /*@Id - данное поле является первичным ключом*/
    @Id
    /*Значения поля должны создаваться автоматически по заданной стратегии.*/
    @GeneratedValue(strategy=GenerationType.AUTO)
    /*@Column - указывает на имя столбца таблицы, который отображается в поле сущности.
    * nullable сообщает JPA, может ли поле быть null или нет.
    * Так же можно задать опцию unique, которая делает конкретное поле уникальным.
    * Оба параметра используются при создании таблиц.*/
    @Column(name = "banner_id", nullable = false)
    private Integer bannerId;

    @Column(name = "img_src",  nullable = false)
    private String imgSrc;

    @Column(name = "width",  nullable = false)
    @Positive
    private Integer width;

    /*Аннотация @Positive указывает, что число должно быть положительным*/
    @Positive
    @Column(name = "height",  nullable = false)
    private Integer height;

    @Column(name = "target_url",  nullable = false)
    private String targetUrl;

    @Column(name = "lang_id",  nullable = false)
    private String langId;

    public Banner(String imgSrc, Integer width,
                  Integer height, String targetUrl, String langId)
    {
        this.imgSrc = imgSrc;
        this.width = width;
        this.height = height;
        this.targetUrl = targetUrl;
        this.langId = langId;
    }

    public Banner() {
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return bannerId.equals(banner.bannerId) &&
                imgSrc.equals(banner.imgSrc) &&
                width.equals(banner.width) &&
                height.equals(banner.height) &&
                targetUrl.equals(banner.targetUrl) &&
                langId.equals(banner.langId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bannerId, imgSrc, width, height, targetUrl, langId);
    }

    @Override
    public String toString()
    {
        return "Banner{" +
                "bannerId=" + bannerId +
                ", imgSrc='" + imgSrc + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", targetUrl='" + targetUrl + '\'' +
                ", langId='" + langId + '\'' +
                '}';
    }
}
