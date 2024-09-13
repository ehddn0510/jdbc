package edu.sm.frame;

import java.util.List;

public interface MService<K, V> {
    V insert(V v) throws Exception;   // add를 insert로 변경
    V update(V v) throws Exception;   // modify를 update로 변경
    Boolean delete(K k) throws Exception;  // remove를 delete로 변경
    V selectOne(K k) throws Exception;  // get을 selectOne으로 변경
    List<V> select() throws Exception;  // get을 select로 변경
}
