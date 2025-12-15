package tut5.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tut5.domain.Beer;
import tut5.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    // Mapping from Entity to DTO — no need to ignore extra entity fields
    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    // Mapping from DTO to Entity — ignore fields managed by DB
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Beer beerDtoToBeer(BeerDto dto);
}
