echo '═══════════════════'
echo '# CONSTRUINDO JAR #'
echo '═══════════════════'
./mvnw clean install -U

echo '═══════════════════════'
echo '# REMOVENDO CONTAINER #'
echo '═══════════════════════'
docker stop db || true && docker rm db || true
docker stop app || true && docker rm app || true

echo '═══════════════════════════════════════════════════'
echo '# INICIANDO TODOS OS SERVIÇOS COM DOCKER-COMPOSER #'
echo '═══════════════════════════════════════════════════'
docker-compose -f docker-compose.yml up