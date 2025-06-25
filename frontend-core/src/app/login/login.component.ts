import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  standalone: true,
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <h2>Login</h2>
    <form [formGroup]="form" (ngSubmit)="login()">
      <input formControlName="username" placeholder="Username">
      <input formControlName="password" type="password" placeholder="Password">
      <button type="submit">Entrar</button>
    </form>
    @if (error) {
      <p style="color:red">{{ error }}</p>
    }
  `
})
export class LoginComponent {
  form: any; // ou melhor: FormGroup
  error = '';

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.auth.login(this.form.value).subscribe({
      next: ({ token }) => {
        this.auth.saveToken(token);
        this.router.navigateByUrl('/home');
      },
      error: (err) => {
        this.error = err.error?.error || 'Erro ao fazer login';
      }
    });
  }
}

