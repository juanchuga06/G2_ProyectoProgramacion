SELECT IdPortada                         
,Portada                                 
,IdLibro                                 
,Estado                                  
,FechaCreacion                           
,FechaModificacion                       
FROM    Portada                          
WHERE   Portada.Estado ='A' AND IdLibro = 1;