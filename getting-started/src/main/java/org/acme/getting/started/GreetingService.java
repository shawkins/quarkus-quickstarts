package org.acme.getting.started;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class GreetingService {

  @Named("first")
  @Inject
  AgroalDataSource first;

  @Named("second")
  @Inject
  AgroalDataSource second;

  public String greeting(String name) {
    try {
      Connection c1 = first.getConnection();
      Connection c2 = second.getConnection();
      return "hello " + name;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
