package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void 아이템_등록() throws Exception {
        //given
        Item album = new Album();
        album.setName("Dawoon");

        //when
        Long savedId = itemService.saveItem(album);

        //then
        assertEquals(album, itemRepository.fineOne(savedId));
    }

    @Test
    public void 아이템_재고_증가() throws Exception {
        //given
        Item movie = new Movie();
        movie.setName("Notebook");
        movie.addStock(10);

        Long saveId = itemService.saveItem(movie);

        //when
        movie.addStock(10);

        //then
        assertEquals(20, itemService.findOne(saveId).getStockQuantity());
    }

    @Test
    public void 아이템_재고_감소() throws Exception {
        //given
        Item movie = new Movie();
        movie.setName("Notebook");
        movie.addStock(10);

        Long saveId = itemService.saveItem(movie);

        //when
        movie.removeStock(5);

        //then
        assertEquals(5, itemService.findOne(saveId).getStockQuantity());
    }
}