--- FOR ENVIRONMENT VARIABLES ---
To set the variable values, go to: "Edit the system environment variables" in your system.
Press "Environment Variables..."
Under System variables, press "New..."
In the space for Variable name, write the name of the variable. These are the same for everyone.
In the space for Variable value, write the value of the variable. These can be different for everyone.
You have to set three environment variables: DB_URL, DB_USER, DB_PASSWORD
EXAMPLE VALUES:
Variable name: DB_URL Variable value: jdbc:postgresql://localhost:5432/wdatabase
Variable name: DB_USER Variable value: postgres
Variable name: DB_PASSWORD Variable value: 12345678
After doing this reload your development environment, so it recognizes the new environment variables. 