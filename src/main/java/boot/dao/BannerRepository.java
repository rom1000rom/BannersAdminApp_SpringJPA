package boot.dao;

import boot.model.Banner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**Интерфейс служит для определения функций хранилища данных об интернет-баннерах
 @author Артемьев Р.А.
 @version 14.11.2019 */
public interface BannerRepository extends CrudRepository<Banner, Integer>
{
    List<Banner> findByTargetUrlLike(String targetUrl);

    List<Banner> findBy();
}

