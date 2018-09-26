FROM centos
MAINTAINER cl935@sina.com

RUN yum install vsftpd -y

RUN useradd ftpuser
RUN echo "123456" | passwd ftpuser --stdin

RUN usermod -s /sbin/nologin ftpuser
RUN mkdir -p /data/ftp/pub
RUN echo "Welcome to use FTP service." > /data/ftp/welcome.txt
RUN chmod a-w /data/ftp && chmod 777 -R /data/ftp/pub
RUN usermod -d /data/ftp ftpuser

RUN sed -i 's/^anonymous_enable=YES/anonymous_enable=NO/g' /etc/vsftpd/vsftpd.conf
RUN sed -i 's/^#chroot_local_user=YES/chroot_local_user=YES/g' /etc/vsftpd/vsftpd.conf
RUN sed -i 's/^#chroot_list_enable=YES/chroot_list_enable=YES/g' /etc/vsftpd/vsftpd.conf
RUN sed -i 's/^#chroot_list_file=\/etc\/vsftpd\/chroot_list/chroot_list_file=\/etc\/vsftpd\/chroot_list/g' /etc/vsftpd/vsftpd.conf

RUN echo  "" > /etc/vsftpd/chroot_list

RUN touch /data/start.sh
RUN echo \#\!/bin/sh >> /data/start.sh
RUN echo "/usr/sbin/vsftpd /etc/vsftpd/vsftpd.conf" >> /data/start.sh
RUN echo "/bin/bash" >> /data/start.sh
RUN chmod +x /data/start.sh

EXPOSE 21
