package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static //실제는 hashMap사용 x (멀티스레드 동시성이슈)
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence); //시퀀스 값 1 증가시키고 (pk)
        store.put(item.getId(), item); // 아이템에 넣은다음
        return item;                //반환한다
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); //컬렉션으로 감싸서 반환하는 이유는 ArrayList에 값을 넣어도 store는 변하지 않기 때문에 안전
    }

    public void update(Long itemId, Item updateParam) { // 정석은 ItemParamDto 같은걸 만들어서 사용안하는 Id를 분리해줘야 하나 예제기때문에 그냥 진행
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
