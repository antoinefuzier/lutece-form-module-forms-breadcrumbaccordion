/*
 * Copyright (c) 2002-2026, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.forms.modules.breadcrumbaccordion.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * Checks that the installation script gives the right of the back office feature to the administrator, so the feature
 * shows in the admin menu after the installation, as the other forms modules do.
 */
public class BreadcrumbAccordionRightTest
{
    private static final Path INIT_CORE = Path.of( "src/sql/plugins/forms/modules/breadcrumbaccordion/core/init_core_forms_breadcrumbaccordion.sql" );

    /**
     * The right the controller requires is declared and given to the administrator (user 1).
     *
     * @throws IOException
     *             if the script cannot be read
     */
    @Test
    public void testInitScriptGivesTheRightToTheAdministrator( ) throws IOException
    {
        String strScript = Files.readString( INIT_CORE, StandardCharsets.UTF_8 );
        String strRight = Pattern.quote( BreadcrumbAccordionJspBean.RIGHT_MANAGE_BREADCRUMB );

        assertTrue( Pattern.compile( "INSERT INTO core_admin_right[^;]*'" + strRight + "'" ).matcher( strScript ).find( ), strScript );
        assertTrue( Pattern.compile( "INSERT INTO core_user_right\\s*\\(id_right,id_user\\)\\s*VALUES\\s*\\('" + strRight + "',1\\);" ).matcher( strScript ).find( ),
                strScript );
    }
}
