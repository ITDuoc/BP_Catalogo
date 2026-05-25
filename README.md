API Catalogo Caso BookPoint
 SpringBoot + JPA + MYSQL



Endpoints de los metodos existentes

Listar todos los libros:
GET /api/v1/catalogo/libros
http://localhost:8082/api/v1/catalogo/libros

Listar todos los articulos:
GET /api/v1/catalogo/articulos
http://localhost:8082/api/v1/catalogo/articulos


Filtros:

Por Autor:
GET /api/v1/catalogo/libros/autor?autor={nombre_autor}
http://localhost:8082/api/v1/catalogo/libros/autor?autor=J.K Rowling

Por Editorial:
GET /api/v1/catalogo/libros/editorial?editorial={nombre_editorial}
http://localhost:8082/api/v1/catalogo/libros/editorial?editorial=Salamandra

Por Genero:
GET /api/v1/catalogo/libros/genero?genero={nombre_genero}
http://localhost:8082/api/v1/catalogo/libros/genero?genero=Fantasia

Por Precio:
GET /api/v1/catalogo/libros/precio?min={precio_minimo}&max={precio_maximo}
http://localhost:8082/api/v1/catalogo/libros/precio?min=10000&max=30000

