Step 0: Make Sure you don't have a database called yellow_movement_site
Step 1: Run "configure-mysql.sql"
Step 2: Run the application

There will be an admin account configured to:
        email: 'admin1@admin.com'
        password: '0000'
        
endpoints:  '/'
            '/login'
            '/createAccount'
            "/home"(USER)
            '/profile'(USER)
            '/post/*'(USER)
            '/post/new'(ADMIN)
            '/blog'(USER)
            '/blog/*'(USER)
            '/blog/new'(BLOGGER)
