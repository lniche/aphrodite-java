package top.threshold.aphrodite;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.dialect.IDialect;
import com.zaxxer.hikari.HikariDataSource;
import top.threshold.aphrodite.pkg.entity.BaseDO;

public class Codegen {

    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/test");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123123");
        GlobalConfig globalConfig = createGlobalConfigUseStyle();
        Generator generator = new Generator(dataSource, globalConfig, IDialect.POSTGRESQL);
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle() {
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.getPackageConfig()
            .setBasePackage("top.threshold.aphrodite.app")
            .setEntityPackage("top.threshold.aphrodite.app.entity.pojo")
            .setServicePackage("top.threshold.aphrodite.app.repository")
            .setServiceImplPackage("top.threshold.aphrodite.app.repository.impl")
            .setMapperPackage("top.threshold.aphrodite.app.mapper");

        globalConfig.getStrategyConfig()
            .setTablePrefix("t_")
            .setGenerateTable("t_user");

        globalConfig.enableEntity()
            .setClassSuffix("DO")
            .setWithLombok(true)
            .setSuperClass(BaseDO.class);

        globalConfig.disableController();
        globalConfig.enableService().setClassSuffix("Repository");
        globalConfig.enableServiceImpl().setClassSuffix("Repository");
        globalConfig.enableMapper();

        return globalConfig;
    }
}
