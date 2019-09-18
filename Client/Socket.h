//
// Created by fabian on 15/09/19.
//

/**
 * Javier Abellán, 20 Junio 2000
 *
 * Funciones de lectura y escritura de la librería de sockets.
 *
 * MODIFICACIONES:
 * 4 de Septiembre de 2003. Añadidas funciones Lee_Socket_Udp(),
 *    Escribe_Socket_Udp() y Dame_Direccion_Udp()
 */
#ifndef CLIENT_SOCKET_H
#define CLIENT_SOCKET_H

#include <sys/socket.h>

/** Lee Datos de tamaño Longitud de un socket cuyo descriptor es fd.
 * Devuelve el numero de bytes leidos o -1 si ha habido error */
int Lee_Socket (int fd, char *Datos, int Longitud);

/** Envia Datos de tamaño Longitud por el socket cuyo descriptor es fd.
 * Devuelve el número de bytes escritos o -1 si ha habido error. */
int Escribe_Socket (int fd, char *Datos, int Longitud);

/** Lee un mensaje de un socket UDP.
 * Se le pasa el descriptor fd del socket que atiende los mensajes.
 * Se le pasa uns estructura sockaddr que nos devolverá rellena con los datos del que nos
 * ha enviado el mensaje, de forma que podamos responderle.
 * Se le pasa el tamaño de la estructura Remoto. En la misma variable nos devolverá el
 * tamaño de los datos devueltos.
 * Se le pasa un buffer de datos para el mensaje y el tamaño en bytes que deseamos leer.
 */
int Lee_Socket_Udp (int fd, struct sockaddr *Remoto, socklen_t *Longitud_Remoto,
                    char *Datos, int Longitud_Datos);

/** Envia un mensaje por un socket UDP
 * Se le pasa el descriptor de socket por el que debe enviar.
 * Se le pasa el destinatario del mensaje en una estructura Remoto.
 * Se le pasa el tamaño de dicha estructura en Longitud_Remoto.
 * Se le pasa el buffer de datos que debe enviar en Datos.
 * Se le pasa la longitud del buffer de datos en Longitud.
 * Devuelve el número de bytes enviados o -1 si ha habido algún error.
 */
int Escribe_Socket_Udp (int fd, struct sockaddr *Remoto,
                        socklen_t Longitud_Remoto, char *Datos, int Longitud);

/**
 * Rellena una estructura sockaddr_in con los datos que se le pasan. Esta estrutura es
 * útil para el envio o recepción de mensajes por sockets Udp o para abrir conexiones.
 * Se le pasa el host. Puede ser NULL (para abrir socket servidor Udp o para recepción de
 * mensajes de cualquier host).
 * Se le pasa el servicio. Puede ser NULL (para abrir socket cliente Udp).
 * Se le pasa una estructura sockaddr_in que devolverá rellena.
 * Se le pasa una Longitud. Debe contener el tamaño de la estructura sockaddr_in y
 * devolverá el tamaño de la estructura una vez rellena.
 * Devuelve -1 en caso de error.
 */
int Dame_Direccion_Udp (char *Host, char *Servicio, struct sockaddr_in *Servidor,
                        int *Longitud);
#endif //CLIENT_SOCKET_H
