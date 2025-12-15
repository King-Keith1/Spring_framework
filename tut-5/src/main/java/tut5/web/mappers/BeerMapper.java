package tut5.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tut5.domain.Beer;
import tut5.web.model.BeerDto;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Beer beerDtoToBeer(BeerDto dto);
}
