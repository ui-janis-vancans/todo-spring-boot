### Running the application with database profile
```bash
./start.sh your-db-profile-name
```
For example: 
```bash
./start.sh db-objectbox
```


### Adding new database profile
- Add profile to `DatabaseProfile` class
- Implement `ToDoRepository` and set the `@Profile` condition for it to load **only when given profile is active**
