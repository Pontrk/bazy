
import psycopg2
#definicja tabel, klucze itp, triggery
def runSQL(com , arguments):
    try:
        con = psycopg2.connect(
        host = "localhost",
        database = "test",
        user ="adm",
        )
        cursor = con.cursor()
        cursor.callproc(com,arguments)
        A = cursor.fetchall()
        print(A)
            
    except Exception as error:
        print(error)
        A = "error"
        
    finally:
        con.commit()
        cursor.close()
        con.close()
        return A
def login_p(l, h):
    try:
        con = psycopg2.connect(
        host = "localhost",
        database = "test",
        user ="adm",
        )
        cursor = con.cursor()
        #cursor.execute("SELECT insert_towar_data('loll','hhh','Jan',NULL,9);")
        cursor.callproc('pracownik_login',[l,h])
        A = cursor.fetchall()[0]
        print(A)
            
    except Exception as error:
        print(error)
        A = "error"
        
    finally:
        con.commit()
        cursor.close()
        con.close()
        return A

def get_data_k(l):
    try:
        con = psycopg2.connect(
        host = "localhost",
        database = "test",
        user ="adm",
        )
        cursor = con.cursor()
        cursor.callproc('klient_get_my_data',[l])
        A = cursor.fetchall()
        print(f'result: {A}')
            
    except Exception as error:
        print(error)
        
    finally:
        con.commit()
        cursor.close()
        con.close()
def get_categories():
    try:
        con = psycopg2.connect(
        host = "localhost",
        database = "test",
        user ="adm",
        )
        cursor = con.cursor()
        cursor.callproc('get_categories')
        A = cursor.fetchall()
        print(f'result: {A}')
            
    except Exception as error:
        print(error)
        
    finally:
        con.commit()
        cursor.close()
        con.close()
def get_categories():
    try:
        con = psycopg2.connect(
        host = "localhost",
        database = "test",
        user ="adm",
        )
        cursor = con.cursor()
        cursor.callproc('get_categories')
        A = cursor.fetchall()
        print(f'result: {A}')
            
    except Exception as error:
        print(error)
        
    finally:
        con.commit()
        cursor.close()
        con.close()
l = 'pracownik1'
h = 'hhh'
#role = login(l, h)
#get_data_k('lll')

'''
pracownik_login  [login,haslo]

get_producent None
get_category None
'''
runSQL("get_producent",None)
runSQL("get_category",None)

