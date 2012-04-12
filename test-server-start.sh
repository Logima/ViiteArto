#!/bin/sh
sh target/bin/webapp &> target/server-output-tests.log &
echo $! > target/server.pid
