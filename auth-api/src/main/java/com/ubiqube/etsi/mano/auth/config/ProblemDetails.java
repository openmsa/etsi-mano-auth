/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.auth.config;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import lombok.Data;

@Data
public class ProblemDetails {
	private URI type = URI.create("about:blank");

	private String title;

	/**
	 * The HTTP status code for this occurrence of the problem. The HTTP status code
	 * ([RFC7231], Section 6) generated by the origin server for this occurrence of
	 * the problem.
	 **/
	private Integer status = 500;

	/**
	 * A human-readable explanation specific to this occurrence of the problem.
	 **/
	private String detail;

	/**
	 * A URI reference that identifies the specific occurrence of the problem. It
	 * may yield further information if dereferenced.
	 **/
	private URI instance;

	public ProblemDetails() {
		try {
			instance = URI.create("http://" + InetAddress.getLocalHost().getCanonicalHostName());
		} catch (final UnknownHostException e) {
			// Nothing.
		}
	}

	public ProblemDetails(final Integer status, final String detail) {
		this();
		this.status = status;
		this.detail = detail;
	}

	public ProblemDetails type(final URI typeIn) {
		this.type = typeIn;
		return this;
	}

	public ProblemDetails title(final String _title) {
		this.title = _title;
		return this;
	}

	public ProblemDetails status(final Integer _status) {
		this.status = _status;
		return this;
	}

	public ProblemDetails detail(final String _detail) {
		this.detail = _detail;
		return this;
	}

	public ProblemDetails instance(final URI instanceIn) {
		this.instance = instanceIn;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ProblemDetails {\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
		sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
