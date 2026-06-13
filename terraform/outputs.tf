output "master_public_ip" {
  value = google_compute_instance.k8s_master.network_interface[0].access_config[0].nat_ip
}

output "worker_public_ips" {
  value = [for w in google_compute_instance.k8s_worker : w.network_interface[0].access_config[0].nat_ip]
}

output "jenkins_public_ip" {
  value = google_compute_instance.jenkins.network_interface[0].access_config[0].nat_ip
}

output "jenkins_url" {
  value = "http://${google_compute_instance.jenkins.network_interface[0].access_config[0].nat_ip}:8080"
}
