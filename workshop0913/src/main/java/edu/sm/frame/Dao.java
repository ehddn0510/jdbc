package edu.sm.frame;

//import edu.sm.exception.DuplicatedIdException;

import java.sql.Connection;
import java.util.List;

public interface Dao<K, V> {
    // insert 하고 난 후 객체를 리턴, 현재는 걍 예외 떤져버리기 (원래는 다 try catch 해야하지만)
    V insert(V v, Connection con) throws Exception;
    V update(V v, Connection con) throws Exception;
    boolean delete(K k, Connection con) throws Exception;
    V select(K k, Connection con) throws Exception;
    List<V> select(Connection con) throws Exception;

    // default 함수로 다른 검색 기능도 구현할 수 있을 것이다. (searchByName)
}
