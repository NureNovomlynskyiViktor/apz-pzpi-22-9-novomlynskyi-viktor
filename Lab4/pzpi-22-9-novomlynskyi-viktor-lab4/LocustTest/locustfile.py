from locust import HttpUser, task, between

class ArtGuardUser(HttpUser):
    wait_time = between(1, 2)

    @task
    def get_objects(self):
        self.client.get("/api/objects")