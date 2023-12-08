package net.kvt_craft.plugins

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.kvt_craft.entities.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabase() {
    val hikariConfig = getHikariConfig()
    val dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)
    transaction {
        SchemaUtils.create(Users)
    }
}

private fun getHikariConfig(): HikariConfig =
    HikariConfig().apply {
        val config = ConfigFactory.load("database.conf")

        dataSourceClassName = config.getString("hikari.dataSourceClassName")
        username = config.getString("hikari.dataSource.user")
        password = config.getString("hikari.dataSource.password")
        maximumPoolSize = config.getInt("hikari.maximumPoolSize")
        jdbcUrl = config.getString("hikari.jdbcUrl")
        isAutoCommit = config.getBoolean("hikari.autoCommit")
        transactionIsolation = config.getString("hikari.transactionIsolation")
        poolName = config.getString("hikari.poolName")
        idleTimeout = config.getLong("hikari.idleTimeout")
        connectionTimeout = config.getLong("hikari.connectionTimeout")
        maxLifetime = config.getLong("hikari.maxLifetime")
    }


