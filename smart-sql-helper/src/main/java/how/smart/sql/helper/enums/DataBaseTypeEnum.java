package how.smart.sql.helper.enums;

/**
 * DataBaseTypeEnum class
 *
 * @author haozhifeng
 * @date 2023/11/29
 */
public enum DataBaseTypeEnum {
    /**
     * Mysql驱动：
     *  <dependency>
     *      <artifactId>mysql-connector-java</artifactId>
     *      <groupId>mysql</groupId>
     *  </dependency>
     */
    MYSQL("mysql", "mysql", "com.mysql.cj.jdbc.Driver", "mysql"),
    /**
     *
     * 使用Greenplum官方提供的驱动：
     *   <dependency>
     *     <groupId>com.greenplum.jdbc</groupId>
     *     <artifactId>greenplum-jdbc-driver</artifactId>
     *     <version>6.23.3</version>
     *   </dependency>
     *
     *   String url = "jdbc:greenplum://your_host:your_port/your_database";
     */
    GP("gp", "greenplum", "com.greenplum.jdbc.Driver", "greenplum"),

    /**
     * 使用Pivotal提供的驱动：
     * (Pivotal是另一个提供Greenplum数据库的企业级软件供应商。
     * 通常与Pivotal的Greenplum数据库发行版一起提供。
     * 这个驱动程序可能与Greenplum数据库的某些特定版本或Pivotal的定制版本有关。)
     *  <dependency>
     *      <groupId>com.pivotal</groupId>
     *      <artifactId>greenplum-jdbc</artifactId>
     *      <version>5.1.4</version>
     *  </dependency>
     *
     *   String url = "jdbc:pivotal:greenplum://your_host:your_port/your_database;user=your_username;password=your_password";
     */
    GP_PIVOTAL("gp_pivotal", "greenplum", "com.pivotal.jdbc.GreenplumDriver", "pivotal:greenplum"),
    /**
     * 使用PostgreSQL数据库的JDBC驱动程序：
     * （org.postgresql是由PostgreSQL数据库官方提供的驱动程序。因此，在支持、维护和更新方面可能会有所不同。
     * Greenplum驱动可能更侧重于与Greenplum数据库的兼容性和功能，而PostgreSQL驱动则更注重与PostgreSQL数据库的互操作性和功能。
     * Greenplum驱动程序通常会支持Greenplum数据库的特定功能和语法，而PostgreSQL驱动则可能支持PostgreSQL数据库的特定功能和语法。
     * 在访问Greenplum数据库时，应考虑所需的功能以及与特定版本的兼容性。）
     *
     *  <dependency>
     *      <groupId>org.postgresql</groupId>
     *      <artifactId>postgresql</artifactId>
     *      <version>42.2.5</version>
     *  </dependency>
     *  String url = "jdbc:postgresql://host:port/database";
     */
    GP_POSTGRESQL("gp_postgresql", "gp_postgresql", "org.postgresql.Driver", "postgresql");;

    /**
     * 数据库名称
     */
    final private String type;
    /**
     * 描述
     */
    final private String desc;
    /**
     * jdbc驱动
     */
    final private String jdbcDriverName;
    /**
     * jdbc子协议
     */
    final private String jdbcSubProtocol;

    DataBaseTypeEnum(String type, String desc, String jdbcDriverName, String jdbcSubProtocol) {
        this.type = type;
        this.desc = desc;
        this.jdbcDriverName = jdbcDriverName;
        this.jdbcSubProtocol = jdbcSubProtocol;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

    public String getJdbcSubProtocol() {
        return jdbcSubProtocol;
    }

    public static void main(String[] args) {
        DataBaseTypeEnum dataBaseType = DataBaseTypeEnum.valueOf("MYSQL");
        System.out.println(dataBaseType.type + " | " + dataBaseType.desc + "  | " + dataBaseType.jdbcDriverName);
    }
}
