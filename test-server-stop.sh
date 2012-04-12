#!/bin/sh
if [ -e "target/server.pid" ]; then
  kill `cat target/server.pid`
  rm -f target/server.pid
else
  echo "no pidfile found"
fi
