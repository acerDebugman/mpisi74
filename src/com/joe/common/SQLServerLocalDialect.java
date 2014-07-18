package com.joe.common;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class SQLServerLocalDialect extends SQLServerDialect{
	public SQLServerLocalDialect(){
		super();
		//Hibernate.STRING 用StandardBasicTypes.STRING替代
		registerFunction("convert", new SQLFunctionTemplate(StandardBasicTypes.STRING, "convert(?1,?2,?3)") );
		registerFunction( "cast", new SQLFunctionTemplate(StandardBasicTypes.STRING, "cast(?1,?2,?3)") );
		//registerFunction("cast", new StandardSQLFunction( "cast" ) );
		registerFunction( "round", new StandardSQLFunction( "round" ) );
		registerFunction( "avg", new StandardSQLFunction( "avg" ) );
	}
}
