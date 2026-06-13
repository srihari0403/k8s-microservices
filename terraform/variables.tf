variable "project_id" {
  default = "project-b8d42e91-125c-4b96-8f5"
}

variable "region" {
  default = "us-central1"
}

variable "zone" {
  default = "us-central1-a"
}

variable "machine_type" {
  default = "e2-standard-2"
}

variable "project_name" {
  default = "k8s-cluster"
}

variable "ssh_user" {
  default = "ubuntu"
}

variable "ssh_pub_key_path" {
  default = "~/.ssh/id_rsa.pub"
}
