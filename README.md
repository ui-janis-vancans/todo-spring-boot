### Running the application with database profile
```bash
./start your-db-profile-name
```
For example: 
```bash
./start db-objectbox
```


### Adding new database profile:
- Add profile to DatabaseProfile class
- Implement ToDoRepository and set the `@Profile` condition for it to load **only when given profile is active**
