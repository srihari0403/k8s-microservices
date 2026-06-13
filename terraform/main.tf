terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 5.0"
    }
  }
}

provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

locals {
  ssh_pub_key = file(var.ssh_pub_key_path)
}

resource "google_compute_instance" "k8s_master" {
  name         = "${var.project_name}-master"
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
      size  = 30
    }
  }

  network_interface {
    subnetwork = google_compute_subnetwork.k8s_subnet.id
    access_config {}
  }

  metadata = {
    ssh-keys       = "${var.ssh_user}:${local.ssh_pub_key}"
    startup-script = file("${path.module}/scripts/master.sh")
  }

  tags = ["k8s-master", "k8s-node"]
}

resource "google_compute_instance" "k8s_worker" {
  count        = 2
  name         = "${var.project_name}-worker-${count.index + 1}"
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
      size  = 30
    }
  }

  network_interface {
    subnetwork = google_compute_subnetwork.k8s_subnet.id
    access_config {}
  }

  metadata = {
    ssh-keys       = "${var.ssh_user}:${local.ssh_pub_key}"
    startup-script = file("${path.module}/scripts/worker.sh")
  }

  tags = ["k8s-worker", "k8s-node"]
}

resource "google_compute_instance" "jenkins" {
  name         = "${var.project_name}-jenkins"
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
      size  = 30
    }
  }

  network_interface {
    subnetwork = google_compute_subnetwork.k8s_subnet.id
    access_config {}
  }

  metadata = {
    ssh-keys       = "${var.ssh_user}:${local.ssh_pub_key}"
    startup-script = file("${path.module}/scripts/jenkins.sh")
  }

  tags = ["jenkins"]
}
