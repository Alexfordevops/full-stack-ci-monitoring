import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  standalone: true,
  selector: 'app-register',
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <h2>Registro</h2>
    <form [formGroup]="form" (ngSubmit)="register()">
      <input formControlName="name" placeholder="Nome">
      <input formControlName="username" placeholder="Username">
      <input formControlName="password" type="password" placeholder="Senha">
      <button type="submit">Registrar</button>
    </form>
    @if (success) {
      <p style="color:green">{{ success }}</p>
    } @else if (error) {
      <p style="color:red">{{ error }}</p>
    }
  `
})
export class RegisterComponent {
  form: any; // ou FormGroup, se quiser tipar
  success = '';
  error = '';

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  register() {
    this.auth.register(this.form.value).subscribe({
      next: () => {
        this.success = 'Usuário registrado com sucesso';
        this.form.reset();
        setTimeout(() => this.router.navigateByUrl('/login'), 1500);
      },
      error: (err) => {
        this.error = err.error?.error || 'Erro ao registrar';
      }
    });
  }
}

