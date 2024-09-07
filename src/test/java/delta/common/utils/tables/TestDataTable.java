package delta.common.utils.tables;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for data tables.
 * @author DAM
 */
@DisplayName("Data table test")
class TestDataTable
{
  private static final String NAME_COLUMN="NAME";
  private static final String DATE_COLUMN="DATE";
  private static final int NB_ROWS=10;

  private DataTable buildTable()
  {
    DataTable table=new DataTable();

    // Name column
    table.addColumn(NAME_COLUMN,String.class);
    DataTableColumn<?> nameColumn=table.getColumnByKey(NAME_COLUMN);
    assertNotNull(nameColumn);
    // Date column
    table.addColumn(DATE_COLUMN,Date.class);
    DataTableColumn<?> dateColumn=table.getColumnByKey(DATE_COLUMN);
    assertNotNull(dateColumn);

    return table;
  }

  private void fillTable(DataTable table)
  {
    DataTableColumn<?> nameColumn=table.getColumnByKey(NAME_COLUMN);
    int nameIndex=nameColumn.getIndex();
    DataTableColumn<?> dateColumn=table.getColumnByKey(DATE_COLUMN);
    int dateIndex=dateColumn.getIndex();
    for(int i=0;i<NB_ROWS;i++)
    {
     table.addRow();
     table.setData(i,nameIndex,"Nom "+i);
     table.setData(i,dateIndex,new Date(System.currentTimeMillis()+1000*i));
    }
  }

  /**
   * Test data table building.
   */
  @Test
  void testBuildTable()
  {
    DataTable table=buildTable();
    assertNotNull(table);
    fillTable(table);
    table.dump(System.out);
  }

  /**
   * Test data table sorting.
   */
  @Test
  void testSortTable()
  {
    DataTable table=buildTable();
    fillTable(table);
    DataTableSort sort=new DataTableSort();
    sort.addSort(DATE_COLUMN,false);
    table.sort(sort);
    table.dump(System.out);
  }
}
