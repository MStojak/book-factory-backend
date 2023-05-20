package hr.fer.is.app.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class PrintJobSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("book_title", table, columnPrefix + "_book_title"));
        columns.add(Column.aliased("writer_name", table, columnPrefix + "_writer_name"));
        columns.add(Column.aliased("page_number", table, columnPrefix + "_page_number"));
        columns.add(Column.aliased("edition_number", table, columnPrefix + "_edition_number"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("start_date", table, columnPrefix + "_start_date"));
        columns.add(Column.aliased("deadline", table, columnPrefix + "_deadline"));
        columns.add(Column.aliased("archive", table, columnPrefix + "_archive"));

        columns.add(Column.aliased("publisher_id", table, columnPrefix + "_publisher_id"));
        columns.add(Column.aliased("cover_type_id", table, columnPrefix + "_cover_type_id"));
        return columns;
    }
}
